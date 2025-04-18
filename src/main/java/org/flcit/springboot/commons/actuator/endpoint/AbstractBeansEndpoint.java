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

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.GenericTypeResolver;
import org.springframework.util.ObjectUtils;

/**
 * @param <T>
 * @param <R>
 * @since 
 * @author Florian Lestic
 */
abstract class AbstractBeansEndpoint<T, R> {

    private final ConfigurableApplicationContext context;

    AbstractBeansEndpoint(ConfigurableApplicationContext context) {
        this.context = context;
    }

    abstract R convert(String name, T value);

    @SuppressWarnings("unchecked")
    private Class<T> getBeanClass() {
        final Class<T>[] clazzes = (Class<T>[]) GenericTypeResolver.resolveTypeArguments(getClass(), AbstractBeansEndpoint.class);
        if (ObjectUtils.isEmpty(clazzes)) {
            throw new IllegalStateException();
        }
        return clazzes[0];
    }

    <B> Map<String, B> listBeans(Class<B> clazz) {
        return context.getBeansOfType(clazz);
    }

    Map<String, T> listBeans() {
        return listBeans(getBeanClass());
    }

    T getBean(String name) {
        return getBean(name, getBeanClass());
    }

    <B> B getBean(String name, Class<B> clazz) {
        return context.getBean(name, clazz);
    }

    Map<String, R> list() {
        final Map<String, T> beans = listBeans();
        Map<String, R> beansConvert = new HashMap<>(beans.size());
        for (Entry<String, T> bean : beans.entrySet()) {
            beansConvert.put(bean.getKey(), convert(bean.getKey(), bean.getValue()));
        }
        return beansConvert;
    }

    R get(@Selector String name) {
        return convert(name, getBean(name));
    }

}
