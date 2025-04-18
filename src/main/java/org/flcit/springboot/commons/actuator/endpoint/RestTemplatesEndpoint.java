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

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import org.flcit.springboot.commons.actuator.clienthttprequestinterceptor.LoggingMessageClientHttpRequestInterceptor;
import org.flcit.springboot.commons.actuator.resttemplate.BaseRestTemplate;
import org.flcit.springboot.commons.actuator.resttemplate.HttpClientTracesConfiguration;
import org.flcit.springboot.commons.actuator.resttemplate.update.RestTemplateAction;
import org.flcit.springboot.commons.actuator.resttemplate.update.RestTemplateUpdate;
import org.flcit.springboot.commons.actuator.util.CommonsActuatorUtils;
import org.flcit.springboot.commons.core.util.BeanUtils;
import org.flcit.commons.core.util.BooleanUtils;
import org.flcit.commons.core.util.ReflectionUtils;

/**
 * 
 * @since 
 * @author Florian Lestic
 */
@Endpoint(id = "resttemplates")
public class RestTemplatesEndpoint extends AbstractBeansEndpoint<RestTemplate, BaseRestTemplate> {

    private static final String FIELD_TRACES = "traces";
    private static final String FIELD_ACTIVE = "active";
    private static final String FIELD_REQUEST = "request";
    private static final String FIELD_RESPONSE = "response";
    private static final String FIELD_HEADERS = "headers";
    private static final String FIELD_BODY = "body";

    /**
     * @param context
     */
    public RestTemplatesEndpoint(ConfigurableApplicationContext context) {
        super(context);
    }

    /**
     *
     */
    @Override
    @ReadOperation
    public Map<String, BaseRestTemplate> list() {
        return super.list();
    }

    /**
     *
     */
    @Override
    @ReadOperation
    public BaseRestTemplate get(@Selector String name) {
        return super.get(name);
    }

    @Override
    BaseRestTemplate convert(String name, RestTemplate value) {
        return CommonsActuatorUtils.convert(name, value);
    }

    /**
     * @param action
     * @throws Exception
     */
    @WriteOperation
    public void action(@Selector RestTemplateAction action) throws Exception {
        for (Entry<String, RestTemplate> entry: listBeans().entrySet()) {
            action(entry.getValue(), action);
        }
    }

    /**
     * @param name
     * @param action
     * @throws Exception
     */
    @WriteOperation
    public void action(@Selector String name, @Selector RestTemplateAction action) throws Exception {
        action(getBean(name), action);
    }

    private void action(final RestTemplate restTemplate, final RestTemplateAction action) throws Exception {
        if (restTemplate == null) {
            return;
        }
        if (action == RestTemplateAction.refreshInterceptors) {
            refreshInterceptors(restTemplate);
        } else if (action == RestTemplateAction.enableTraces) {
            enableTraces(restTemplate);
        } else if (action == RestTemplateAction.disableTraces) {
            disableTraces(restTemplate);
        } else if (action == RestTemplateAction.active) {
            activation(restTemplate, true);
        } else if (action == RestTemplateAction.inactive) {
            activation(restTemplate, false);
        }
    }

    private static final void refreshInterceptors(final RestTemplate restTemplate) {
        if (!CollectionUtils.isEmpty(restTemplate.getInterceptors())) {
            for (ClientHttpRequestInterceptor interceptor: restTemplate.getInterceptors()) {
                ClientHttpRequestInterceptorsEndpoint.refresh(interceptor);
            }
        }
    }

    private static final void enableTraces(final RestTemplate restTemplate) throws Exception {
        final Object traces = CommonsActuatorUtils.getSafeFieldOrInitialize(restTemplate, FIELD_TRACES);
        if (traces == null) {
            return;
        }
        enableTracesMessage(CommonsActuatorUtils.getSafeFieldOrInitialize(traces, FIELD_REQUEST));
        enableTracesMessage(CommonsActuatorUtils.getSafeFieldOrInitialize(traces, FIELD_RESPONSE));
        LoggingSystem.get(RestTemplatesEndpoint.class.getClassLoader()).setLogLevel("org.flcit.springboot.http.client.core.interceptor.logging.BaseLoggingClientInterceptor", LogLevel.INFO);
        BeanUtils.initializing(restTemplate);
    }

    private static void enableTracesMessage(Object tracesMessage) {
        if (tracesMessage == null) {
            return;
        }
        ReflectionUtils.setFieldValue(tracesMessage, FIELD_ACTIVE, Boolean.TRUE);
        ReflectionUtils.setFieldValue(tracesMessage, FIELD_HEADERS, Boolean.TRUE);
        ReflectionUtils.setFieldValue(tracesMessage, FIELD_BODY, Boolean.TRUE);
    }

    private static final void disableTraces(final RestTemplate restTemplate) throws Exception {
        final Field field = org.springframework.util.ReflectionUtils.findField(restTemplate.getClass(), FIELD_TRACES);
        if (field != null
                && ReflectionUtils.getFieldValue(restTemplate, field) != null) {
            ReflectionUtils.setFieldValue(restTemplate, FIELD_TRACES, null);
            BeanUtils.initializing(restTemplate);
        }
    }

    private static final void activation(final RestTemplate restTemplate, boolean active) {
        ReflectionUtils.setFieldValue(restTemplate, FIELD_ACTIVE, active);
    }

    /**
     * @param name
     * @param url
     * @param simpleClientHttp
     * @param connectTimeout
     * @param connectionRequestTimeout
     * @param socketTimeout
     * @param chunkSize
     * @param maxConnectionPerRoute
     * @param maxConnectionTotal
     * @param proxy
     * @param streaming
     * @param sslCertificateVerification
     * @param queryParamsListMode
     * @param tracesActive
     * @param tracesRequestActive
     * @param tracesResponseActive
     * @param tracesRequestHeaders
     * @param tracesResponseHeaders
     * @param tracesRequestBody
     * @param tracesResponseBody
     * @param tracesRequestMaxLength
     * @param tracesResponseMaxLength
     * @throws Exception
     */
    @SuppressWarnings("java:S107")
    @WriteOperation
    public void update(@Selector String name,
            @Nullable String url,
            @Nullable Boolean simpleClientHttp,
            @Nullable Integer connectTimeout,
            @Nullable Integer connectionRequestTimeout,
            @Nullable Integer socketTimeout,
            @Nullable Integer chunkSize,
            @Nullable Integer maxConnectionPerRoute,
            @Nullable Integer maxConnectionTotal,
            @Nullable Boolean proxy,
            @Nullable Boolean streaming,
            @Nullable Boolean sslCertificateVerification,
            @Nullable String queryParamsListMode,
            @Nullable Boolean tracesActive,
            @Nullable Boolean tracesRequestActive,
            @Nullable Boolean tracesResponseActive,
            @Nullable Boolean tracesRequestHeaders,
            @Nullable Boolean tracesResponseHeaders,
            @Nullable Boolean tracesRequestBody,
            @Nullable Boolean tracesResponseBody,
            @Nullable Integer tracesRequestMaxLength,
            @Nullable Integer tracesResponseMaxLength) throws Exception {
        final RestTemplate restTemplate = getBean(name);
        BeanUtils.copyNonNullProperties(
                new RestTemplateUpdate()
                .setUrl(url)
                .setSimpleClientHttp(simpleClientHttp)
                .setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .setSocketTimeout(socketTimeout)
                .setChunkSize(chunkSize)
                .setMaxConnectionPerRoute(maxConnectionPerRoute)
                .setMaxConnectionTotal(maxConnectionTotal)
                .setProxy(proxy)
                .setStreaming(streaming)
                .setSslCertificateVerification(sslCertificateVerification)
                .setQueryParamsListMode(queryParamsListMode)
                .setTraces(convert(
                        tracesResponseActive,
                        convert(tracesRequestActive, tracesRequestHeaders, tracesRequestBody, tracesRequestMaxLength),
                        convert(tracesResponseActive, tracesResponseHeaders, tracesResponseBody, tracesResponseMaxLength))),
                restTemplate);
        CommonsActuatorUtils.setFieldEnum(restTemplate, "queryParamsListMode", queryParamsListMode);
        BeanUtils.initializing(restTemplate);
    }

    private static final HttpClientTracesConfiguration convert(Boolean active, LoggingMessageClientHttpRequestInterceptor request, LoggingMessageClientHttpRequestInterceptor response) {
        if (Boolean.FALSE.equals(active)
                || (!isActive(request) && !isActive(response))) {
            return null;
        }
        return (HttpClientTracesConfiguration) new HttpClientTracesConfiguration()
        .setActive(active)
        .setRequest(request)
        .setResponse(response);
    }

    private static final LoggingMessageClientHttpRequestInterceptor convert(Boolean active, Boolean headers, Boolean body, Integer maxLength) {
        return new LoggingMessageClientHttpRequestInterceptor()
                .setHeaders(headers)
                .setActive(active)
                .setBody(body)
                .setMaxLength(maxLength);
    }

    private static final boolean isActive(LoggingMessageClientHttpRequestInterceptor trace) {
        return trace != null && BooleanUtils.isTrue(trace.getActive());
    }

}
