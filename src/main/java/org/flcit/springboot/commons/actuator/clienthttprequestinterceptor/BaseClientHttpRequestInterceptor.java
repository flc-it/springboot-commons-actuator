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

package org.flcit.springboot.commons.actuator.clienthttprequestinterceptor;

import java.net.HttpCookie;
import java.util.List;
import java.util.Map;

import org.springframework.http.client.ClientHttpRequestInterceptor;

import org.flcit.springboot.commons.actuator.clienthttprequestinterceptor.update.BaseClientHttpRequestInterceptorUpdate;
import org.flcit.springboot.commons.actuator.util.CommonsActuatorUtils;
import org.flcit.commons.core.util.ObjectUtils;
import org.flcit.commons.core.util.ReflectionUtils;
import org.flcit.commons.core.util.StringUtils;

/**
 * 
 * @since 
 * @author Florian Lestic
 */
public class BaseClientHttpRequestInterceptor extends BaseClientHttpRequestInterceptorUpdate {

    private final String name;
    private final Class<? extends ClientHttpRequestInterceptor> type;
    /*
     * Token
     */
    private final Object token;
    private final Long expire;
    /*
     * Cookie
     */
    private final HttpCookie cookie;
    private final Map<String, List<HttpCookie>> cookies;

    /*
     * Logging
     */
    private final LoggingMessageClientHttpRequestInterceptor request;
    private final LoggingMessageClientHttpRequestInterceptor response;

    /**
     * @param name
     * @param interceptor
     */
    @SuppressWarnings("unchecked")
    public BaseClientHttpRequestInterceptor(final String name, final ClientHttpRequestInterceptor interceptor) {
        this.name = ObjectUtils.getOrDefault(name, ReflectionUtils.getSafeFieldValue(interceptor, "beanName", String.class));
        this.type = interceptor.getClass();
        this.setService(ReflectionUtils.getSafeFieldValue(interceptor, "service", String.class));
        this.setUrl(ReflectionUtils.getSafeFieldValue(interceptor, "url", String.class));
        this.setUsername(ReflectionUtils.getSafeFieldValue(interceptor, "username", String.class));
        this.setPassword(ReflectionUtils.getSafeFieldValue(interceptor, "password", String.class));
        this.token = ReflectionUtils.getSafeFieldValue(interceptor, "token");
        this.expire = ObjectUtils.getOrDefault(ReflectionUtils.getSafeFieldValue(interceptor, "expire", Long.class), () -> ReflectionUtils.getSafeFieldValue(interceptor, "expire", long.class));
        this.setClientId(ReflectionUtils.getSafeFieldValue(interceptor, "clientId", String.class));
        this.setClientSecret(ReflectionUtils.getSafeFieldValue(interceptor, "clientSecret", String.class));
        this.setGrantType(StringUtils.convert(ReflectionUtils.getSafeFieldValue(interceptor, "grantType")));
        this.setScope(ReflectionUtils.getSafeFieldValue(interceptor, "scope", String.class));
        this.setCookieName(ReflectionUtils.getSafeFieldValue(interceptor, "cookieName", String.class));
        this.setCookiesNames(ReflectionUtils.getSafeFieldValue(interceptor, "cookiesNames", String[].class));
        this.setCookiesNamesInHeader(ReflectionUtils.getSafeFieldValue(interceptor, "cookiesNamesInHeader", String[].class));
        this.cookie = ReflectionUtils.getSafeFieldValue(interceptor, "cookie", HttpCookie.class);
        this.cookies = ReflectionUtils.getSafeFieldValue(interceptor, "cookies", Map.class);
        this.request = CommonsActuatorUtils.convertTracesMessage(ReflectionUtils.getSafeFieldValue(interceptor, "request"));
        this.response = CommonsActuatorUtils.convertTracesMessage(ReflectionUtils.getSafeFieldValue(interceptor, "response"));
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
    public Class<? extends ClientHttpRequestInterceptor> getType() {
        return type;
    }

    /**
     * @return
     */
    public Object getToken() {
        return token;
    }

    /**
     * @return
     */
    public Long getExpire() {
        return expire;
    }

    /**
     * @return
     */
    public HttpCookie getCookie() {
        return cookie;
    }

    /**
     * @return
     */
    public Map<String, List<HttpCookie>> getCookies() {
        return cookies;
    }

    /**
     * @return
     */
    public LoggingMessageClientHttpRequestInterceptor getRequest() {
        return request;
    }

    /**
     * @return
     */
    public LoggingMessageClientHttpRequestInterceptor getResponse() {
        return response;
    }

}
