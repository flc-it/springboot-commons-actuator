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

package org.flcit.springboot.commons.actuator.util;

import java.lang.reflect.Field;
import java.util.concurrent.Executor;

import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.util.Assert;
import org.springframework.util.backoff.BackOff;
import org.springframework.web.client.RestTemplate;

import org.flcit.springboot.commons.actuator.clienthttprequestinterceptor.BaseClientHttpRequestInterceptor;
import org.flcit.springboot.commons.actuator.clienthttprequestinterceptor.LoggingMessageClientHttpRequestInterceptor;
import org.flcit.springboot.commons.actuator.executor.BaseExecutor;
import org.flcit.springboot.commons.actuator.executor.SimpleAsyncTaskExecutorDTO;
import org.flcit.springboot.commons.actuator.executor.ThreadPoolTaskExecutorDTO;
import org.flcit.springboot.commons.actuator.executor.ThreadPoolTaskSchedulerDTO;
import org.flcit.springboot.commons.actuator.jms.BaseJmsListener;
import org.flcit.springboot.commons.actuator.jms.DefaultJmsListener;
import org.flcit.springboot.commons.actuator.resttemplate.BaseRestTemplate;
import org.flcit.springboot.commons.actuator.resttemplate.HttpClientTracesConfiguration;
import org.flcit.springboot.commons.core.util.BeanUtils;
import org.flcit.commons.core.util.EnumUtils;
import org.flcit.commons.core.util.ReflectionUtils;

/**
 * 
 * @since 
 * @author Florian Lestic
 */
public final class CommonsActuatorUtils {

    private CommonsActuatorUtils() { }

    /**
     * @param id
     * @param listener
     * @return
     */
    public static BaseJmsListener convert(final String id, final MessageListenerContainer listener) {
        Assert.notNull(id, "Id must not be null");
        Assert.notNull(listener, "Listener must not be null");
        return listener instanceof DefaultMessageListenerContainer ? new DefaultJmsListener(id, (DefaultMessageListenerContainer) listener) : new BaseJmsListener(id, listener);
    }

    /**
     * @param executor
     * @return
     */
    public static BaseExecutor convert(final Executor executor) {
        return convert(null, executor);
    }

    /**
     * @param name
     * @param executor
     * @return
     */
    public static BaseExecutor convert(final String name, final Executor executor) {
        Assert.notNull(executor, "Executor must not be null");
        if (executor instanceof ThreadPoolTaskScheduler) {
            return new ThreadPoolTaskSchedulerDTO(name, (ThreadPoolTaskScheduler) executor);
        } else if (executor instanceof ThreadPoolTaskExecutor) {
            return new ThreadPoolTaskExecutorDTO(name, (ThreadPoolTaskExecutor) executor);
        } else if (executor instanceof SimpleAsyncTaskExecutor) {
            return new SimpleAsyncTaskExecutorDTO(name, (SimpleAsyncTaskExecutor) executor);
        }
        return null;
    }

    /**
     * @param listener
     * @return
     */
    public static BackOff getBackOff(MessageListenerContainer listener) {
        return ReflectionUtils.getSafeFieldValue(listener, "backOff", BackOff.class);
    }

    /**
     * @param name
     * @param restTemplate
     * @return
     */
    public static BaseRestTemplate convert(final String name, RestTemplate restTemplate) {
        Assert.notNull(restTemplate, "RestTemplate must not be null");
        return new BaseRestTemplate(name, restTemplate);
    }

    /**
     * @param interceptor
     * @return
     */
    public static BaseClientHttpRequestInterceptor convert(ClientHttpRequestInterceptor interceptor) {
        return convert(null, interceptor);
    }

    /**
     * @param name
     * @param interceptor
     * @return
     */
    public static BaseClientHttpRequestInterceptor convert(final String name, ClientHttpRequestInterceptor interceptor) {
        Assert.notNull(interceptor, "Interceptor must not be null");
        return new BaseClientHttpRequestInterceptor(name, interceptor);
    }

    /**
     * @param traces
     * @return
     */
    public static HttpClientTracesConfiguration convertTraces(final Object traces) {
        if (traces == null) {
            return null;
        }
        final HttpClientTracesConfiguration tracesConfiguration = new HttpClientTracesConfiguration();
        tracesConfiguration.setActive(ReflectionUtils.getSafeFieldValue(traces, "active", Boolean.class));
        tracesConfiguration.setRequest(convertTracesMessage(ReflectionUtils.getSafeFieldValue(traces, "request")));
        tracesConfiguration.setResponse(convertTracesMessage(ReflectionUtils.getSafeFieldValue(traces, "response")));
        return tracesConfiguration;
    }

    /**
     * @param tracesMessages
     * @return
     */
    public static LoggingMessageClientHttpRequestInterceptor convertTracesMessage(final Object tracesMessages) {
        if (tracesMessages == null) {
            return null;
        }
        final LoggingMessageClientHttpRequestInterceptor tracesMessagesInterceptor = new LoggingMessageClientHttpRequestInterceptor();
        BeanUtils.copyNonNullProperties(tracesMessages, tracesMessagesInterceptor);
        return tracesMessagesInterceptor;
    }

    /**
     * @param object
     * @param name
     * @return
     */
    public static Object getSafeFieldOrInitialize(final Object object, final String name) {
        try {
            return getFieldOrInitialize(object, name);
        } catch (ReflectiveOperationException e) {
            return null;
        }
    }

    /**
     * @param object
     * @param name
     * @return
     * @throws ReflectiveOperationException
     */
    @SuppressWarnings("java:S1874")
    public static Object getFieldOrInitialize(final Object object, final String name) throws ReflectiveOperationException {
        final Field field = org.springframework.util.ReflectionUtils.findField(object.getClass(), name);
        if (field == null) {
            throw new NoSuchFieldException("No Such Field " + name);
        }
        Object obj = ReflectionUtils.getFieldValue(object, field);
        if (obj == null) {
            obj = field.getType().newInstance();
            ReflectionUtils.setFieldValue(object, field, obj);
        }
        return obj;
    }

    /**
     * @param object
     * @param fieldName
     * @param value
     */
    @SuppressWarnings("unchecked")
    public static void setFieldEnum(Object object, String fieldName, String value) {
        final Field field = org.springframework.util.ReflectionUtils.findField(object.getClass(), fieldName);
        if (field != null
                && field.getType().isEnum()) {
            Object obj = EnumUtils.convertSafeOnNameOrToString(value, (Class<? extends Enum<?>>) field.getType());
            if (obj != null) {
                ReflectionUtils.setFieldValue(object, field, obj);
            }
        }
    }

}
