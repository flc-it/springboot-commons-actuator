/*
 * Copyright 2002-2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.flcit.springboot.commons.actuator.endpoint;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;

import org.flcit.springboot.commons.actuator.configuration.ConfigurationAction;
import org.flcit.springboot.commons.actuator.configuration.ConfigurationSearchOperator;
import org.flcit.springboot.commons.actuator.configuration.ConfigurationType;
import org.flcit.springboot.commons.core.util.BeanUtils;

/**
 * 
 * @since 
 * @author Florian Lestic
 */
@Endpoint(id = "configuration", enableByDefault = false)
public class ConfigurationEndpoint {

    /**
     * 
     */
    public static final String PROPERTY_SOURCE_DYNAMIC = "PROPERTY_SOURCE_DYNAMIC";
    /**
     * 
     */
    public static final String PROPERTY_SOURCE_DATABASE = "PROPERTY_SOURCE_DATABASE";

    private final ConfigurableApplicationContext context;

    /**
     * @param context
     */
    public ConfigurationEndpoint(ConfigurableApplicationContext context) {
        this.context = context;
    }

    /**
     * @param type
     * @return
     * @throws IOException
     */
    @ReadOperation(produces = MediaType.TEXT_PLAIN_VALUE)
    public byte[] list(@Nullable ConfigurationType type) throws IOException {
        return type == null ? list() : listByType(type);
    }

    /**
     * @param name
     * @param operator
     * @return
     * @throws IOException
     */
    @ReadOperation(produces = MediaType.TEXT_PLAIN_VALUE)
    public byte[] find(@Selector String name, @Nullable ConfigurationSearchOperator operator) throws IOException {
        return operator == null ? get(name) : search(name, operator);
    }

    /**
     * @param type
     * @param name
     * @param value
     */
    @WriteOperation
    public void put(@Selector ConfigurationType type, String name, @Nullable Object value) {
        if (type == ConfigurationType.dynamic) {
            getOrCreatePropertySource(PROPERTY_SOURCE_DYNAMIC).getSource().put(name, value);
        }
    }

    /**
     * @param action
     */
    @WriteOperation
    public void action(@Selector ConfigurationAction action) {
        if (action == ConfigurationAction.refreshBeans) {
            BeanUtils.refreshBeans(context);
        }
    }

    /**
     * @param type
     */
    @DeleteOperation
    public void delete(@Selector ConfigurationType type) {
        if (type == ConfigurationType.dynamic) {
            delete(PROPERTY_SOURCE_DYNAMIC);
        }
    }

    /**
     * @param type
     * @param name
     */
    @DeleteOperation
    public void delete(@Selector ConfigurationType type, @Selector String name) {
        if (type == ConfigurationType.dynamic) {
            delete(PROPERTY_SOURCE_DYNAMIC, name);
        }
    }

    private void delete(final String propertySourceName, final String propertyName) {
        final Map<String, ?> propertySource = getPropertySource(propertySourceName);
        if (propertySource != null) {
            propertySource.remove(propertyName);
        }
    }

    private void delete(final String propertySourceName) {
        final PropertySource<?> propertySourceRemoved = this.context.getEnvironment().getPropertySources().remove(propertySourceName);
        if (propertySourceRemoved != null) {
            BeanUtils.refreshBeans(context);
        }
    }

    private byte[] list() throws IOException {
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        writeln(os, String.format("**********************************   %s   **********************************", "Runtime"));
        write(os, getRuntime());
        for (PropertySource<?> propertySource : context.getEnvironment().getPropertySources()) {
            if (propertySource instanceof EnumerablePropertySource) {
                final String[] propertyNames = ((EnumerablePropertySource<?>) propertySource).getPropertyNames();
                if (!ObjectUtils.isEmpty(propertyNames)) {
                    writeln(os, String.format("**********************************   %s   **********************************", propertySource.getName()));
                    for (String name : propertyNames) {
                        writeln(os, name, propertySource.getProperty(name));
                    }
                }
            }
        }
        return toResponse(os);
    }

    private byte[] listByType(ConfigurationType type) throws IOException {
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        switch (type) {
        case runtime:
            write(os, getRuntime());
            break;
        case system:
            write(os, context.getEnvironment().getSystemProperties());
            break;
        case environment:
            write(os, context.getEnvironment().getSystemEnvironment());
            break;
        case application:
            write(os, getApplicationPropertySource());
            break;
        case dynamic:
            write(os, getPropertySource(PROPERTY_SOURCE_DYNAMIC));
            break;
        case database:
            write(os, getPropertySource(PROPERTY_SOURCE_DATABASE));
            break;
        default:
            break;
        }
        return toResponse(os);
    }

    private byte[] get(String name) throws IOException {
        final String value = context.getEnvironment().getProperty(name);
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        if (value != null) {
            writeln(os, value);
        }
        return os.toByteArray();
    }

    private byte[] search(String name, ConfigurationSearchOperator operator) throws IOException {
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        for (PropertySource<?> propertySource : context.getEnvironment().getPropertySources()) {
            if (propertySource instanceof EnumerablePropertySource) {
                final String[] propertyNames = ((EnumerablePropertySource<?>) propertySource).getPropertyNames();
                if (!ObjectUtils.isEmpty(propertyNames)) {
                    for (String propertyName : propertyNames) {
                        if (match(name, propertyName, operator)) {
                            writeln(os, propertyName, propertySource.getProperty(propertyName));
                        }
                    }
                }
            }
        }
        return toResponse(os);
    }

    private static final boolean match(String name, String propertyName, ConfigurationSearchOperator operator) {
        switch (operator) {
        case contains:
            return propertyName.contains(name);
        case equals:
            return propertyName.equals(name);
        case endsWith:
            return propertyName.endsWith(name);
        case startsWith:
            return propertyName.startsWith(name);
        default:
            return false;
        }
    }

    private static final Map<String, Object> getRuntime() {
        final Map<String, Object> runtime = new HashMap<>(4);
        runtime.put("availableProcessors", Runtime.getRuntime().availableProcessors());
        runtime.put("freeMemory", Runtime.getRuntime().freeMemory());
        runtime.put("maxMemory", Runtime.getRuntime().maxMemory());
        runtime.put("totalMemory", Runtime.getRuntime().totalMemory());
        return runtime;
    }

    private static final byte[] toResponse(ByteArrayOutputStream os) {
        return os == null || os.size() == 0 ? null : os.toByteArray();
    }

    private static final void write(OutputStream os, Map<String, ?> propertySource) throws IOException {
        if (propertySource != null) {
            for (Entry<String, ?> e : propertySource.entrySet()) {
                writeln(os, e.getKey(), e.getValue());
            }
        }
    }

    private static final void writeln(OutputStream os, String line) throws IOException {
        os.write(line.getBytes());
        os.write('\n');
    }

    private static final void writeln(OutputStream os, String key, Object value) throws IOException {
        writeln(os, toBytes(key), toBytes(value));
    }

    private static final void writeln(OutputStream os, byte[] key, byte[] value) throws IOException {
        if (!ObjectUtils.isEmpty(value)) {
            os.write(key);
        }
        os.write('=');
        if (!ObjectUtils.isEmpty(value)) {
            os.write(value);
        }
        os.write('\n');
    }

    private static final byte[] toBytes(Object value) {
        if (value == null) {
            return new byte[0];
        }
        if (value instanceof String) {
            return ((String) value).getBytes();
        }
        return toBytes(value.toString());
    }

    private static final void write(OutputStream os, List<? extends EnumerablePropertySource<Map<String, Object>>> propertySource) throws IOException {
        for (EnumerablePropertySource<Map<String, Object>> e : propertySource) {
            write(os, e.getSource());
            os.write('\n');
        }
    }

    @SuppressWarnings("unchecked")
    private PropertySource<Map<String, Object>> getOrCreatePropertySource(final String propertySourceName) {
        PropertySource<Map<String, Object>> propertySource = (PropertySource<Map<String, Object>>) context.getEnvironment().getPropertySources().get(propertySourceName);
        if (propertySource == null) {
            propertySource = new MapPropertySource(propertySourceName, new LinkedHashMap<>());
            context.getEnvironment().getPropertySources().addFirst(propertySource);
        }
        return propertySource;
    }

    /**
     * @param propertySourceName
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> getPropertySource(final String propertySourceName) {
        final PropertySource<Map<String, Object>> propertySource = (PropertySource<Map<String, Object>>) context.getEnvironment().getPropertySources().get(propertySourceName);
        return propertySource != null ? propertySource.getSource() : null;
    }

    private List<OriginTrackedMapPropertySource> getApplicationPropertySource() {
        return getPropertySourceByTypeEquals(OriginTrackedMapPropertySource.class);
    }

    @SuppressWarnings("unchecked")
    private <T extends PropertySource<?>> List<T> getPropertySourceByTypeEquals(Class<T> clazz) {
        final List<T> res = new ArrayList<>(context.getEnvironment().getPropertySources().size());
        for (PropertySource<?> propertySource : context.getEnvironment().getPropertySources()) {
            if (clazz.equals(propertySource.getClass())) {
                res.add((T) propertySource);
            }
        }
        return res;
    }

}
