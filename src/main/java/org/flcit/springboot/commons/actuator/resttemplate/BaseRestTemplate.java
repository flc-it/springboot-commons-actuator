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

package org.flcit.springboot.commons.actuator.resttemplate;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInitializer;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplateHandler;

import org.flcit.springboot.commons.actuator.clienthttprequestinterceptor.BaseClientHttpRequestInterceptor;
import org.flcit.springboot.commons.actuator.resttemplate.update.RestTemplateUpdate;
import org.flcit.springboot.commons.actuator.util.CommonsActuatorUtils;
import org.flcit.commons.core.util.ClassUtils;
import org.flcit.commons.core.util.ObjectUtils;
import org.flcit.commons.core.util.ReflectionUtils;
import org.flcit.commons.core.util.StringUtils;

/**
 * 
 * @since 
 * @author Florian Lestic
 */
public class BaseRestTemplate extends RestTemplateUpdate {

    private final String name;
    private final Class<? extends RestTemplate> type;
    private final Class<? extends ResponseErrorHandler> errorHandler;
    private final Class<? extends UriTemplateHandler> uriTemplateHandler;
    private final List<Class<ClientHttpRequestInitializer>> clientHttpRequestInitializers;
    private final Class<? extends ClientHttpRequestFactory> requestFactory;
    private final List<Class<HttpMessageConverter<?>>> messageConverters;
    private final List<BaseClientHttpRequestInterceptor> interceptors;

    /**
     * @param name
     * @param restTemplate
     */
    public BaseRestTemplate(final String name, final RestTemplate restTemplate) {
        this.setUrl(ReflectionUtils.getSafeFieldValue(restTemplate, "url", String.class));
        this.setSimpleClientHttp(ReflectionUtils.getSafeFieldValue(restTemplate, "simpleClientHttp", Boolean.class));
        this.setConnectTimeout(ReflectionUtils.getSafeFieldValue(restTemplate, "connectTimeout", Integer.class));
        this.setConnectionRequestTimeout(ReflectionUtils.getSafeFieldValue(restTemplate, "connectionRequestTimeout", Integer.class));
        this.setSocketTimeout(ReflectionUtils.getSafeFieldValue(restTemplate, "socketTimeout", Integer.class));
        this.setChunkSize(ReflectionUtils.getSafeFieldValue(restTemplate, "chunkSize", Integer.class));
        this.setMaxConnectionPerRoute(ReflectionUtils.getSafeFieldValue(restTemplate, "maxConnectionPerRoute", Integer.class));
        this.setMaxConnectionTotal(ReflectionUtils.getSafeFieldValue(restTemplate, "maxConnectionTotal", Integer.class));
        this.setProxy(ReflectionUtils.getSafeFieldValue(restTemplate, "proxy", Boolean.class));
        this.setStreaming(ReflectionUtils.getSafeFieldValue(restTemplate, "streaming", Boolean.class));
        this.setSslCertificateVerification(ReflectionUtils.getSafeFieldValue(restTemplate, "sslCertificateVerification", Boolean.class));
        this.setTraces(CommonsActuatorUtils.convertTraces(ReflectionUtils.getSafeFieldValue(restTemplate, "traces")));
        this.setQueryParamsListMode(StringUtils.convert(ReflectionUtils.getSafeFieldValue(restTemplate, "queryParamsListMode")));
        this.name = ObjectUtils.getOrDefault(name, ReflectionUtils.getSafeFieldValue(restTemplate, "beanName", String.class));
        this.type = restTemplate.getClass(); 
        this.errorHandler = ClassUtils.getSafe(restTemplate.getErrorHandler());
        this.uriTemplateHandler = ClassUtils.getSafe(restTemplate.getUriTemplateHandler());
        this.clientHttpRequestInitializers = ClassUtils.listSafe(restTemplate.getClientHttpRequestInitializers());
        this.requestFactory = ClassUtils.getSafe(restTemplate.getRequestFactory());
        this.messageConverters = ClassUtils.listSafe(restTemplate.getMessageConverters());
        this.interceptors = !CollectionUtils.isEmpty(restTemplate.getInterceptors()) ? restTemplate.getInterceptors().stream().map(CommonsActuatorUtils::convert).collect(Collectors.toList()) : null;
    }

    /**
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @return
     */
    public Class<? extends RestTemplate> getType() {
        return type;
    }

    /**
     * @return
     */
    public Class<? extends ResponseErrorHandler> getErrorHandler() {
        return errorHandler;
    }

    /**
     * @return
     */
    public Class<? extends UriTemplateHandler> getUriTemplateHandler() {
        return uriTemplateHandler;
    }

    /**
     * @return
     */
    public List<Class<ClientHttpRequestInitializer>> getClientHttpRequestInitializers() {
        return clientHttpRequestInitializers;
    }

    /**
     * @return
     */
    public Class<? extends ClientHttpRequestFactory> getRequestFactory() {
        return requestFactory;
    }

    /**
     * @return
     */
    @SuppressWarnings("java:S1452")
    public List<Class<HttpMessageConverter<?>>> getMessageConverters() {
        return messageConverters;
    }

    /**
     * @return
     */
    public List<BaseClientHttpRequestInterceptor> getInterceptors() {
        return interceptors;
    }

}
