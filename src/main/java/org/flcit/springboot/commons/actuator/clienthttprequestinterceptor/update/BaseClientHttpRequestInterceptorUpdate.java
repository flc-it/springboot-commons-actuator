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

package org.flcit.springboot.commons.actuator.clienthttprequestinterceptor.update;

/**
 * 
 * @since 
 * @author Florian Lestic
 */
public class BaseClientHttpRequestInterceptorUpdate {

    /*
     * URL
     */
    private String service;
    private String url;
    /*
     * Basic
     */
    private String username;
    private String password;
    /*
     * OAuth2
     */
    private String clientId;
    private String clientSecret;
    private String grantType;
    private String scope;
    /*
     * Cookie
     */
    private String cookieName;
    private String[] cookiesNames;
    private String[] cookiesNamesInHeader;

    /**
     * @return
     */
    public String getService() {
        return service;
    }
    /**
     * @param service
     * @return
     */
    public BaseClientHttpRequestInterceptorUpdate setService(String service) {
        this.service = service;
        return this;
    }
    /**
     * @return
     */
    public String getUrl() {
        return url;
    }
    /**
     * @param url
     * @return
     */
    public BaseClientHttpRequestInterceptorUpdate setUrl(String url) {
        this.url = url;
        return this;
    }
    /**
     * @return
     */
    public String getUsername() {
        return username;
    }
    /**
     * @param username
     * @return
     */
    public BaseClientHttpRequestInterceptorUpdate setUsername(String username) {
        this.username = username;
        return this;
    }
    /**
     * @return
     */
    public String getPassword() {
        return password;
    }
    /**
     * @param password
     * @return
     */
    public BaseClientHttpRequestInterceptorUpdate setPassword(String password) {
        this.password = password;
        return this;
    }
    /**
     * @return
     */
    public String getClientId() {
        return clientId;
    }
    /**
     * @param clientId
     * @return
     */
    public BaseClientHttpRequestInterceptorUpdate setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }
    /**
     * @return
     */
    public String getClientSecret() {
        return clientSecret;
    }
    /**
     * @param clientSecret
     * @return
     */
    public BaseClientHttpRequestInterceptorUpdate setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
        return this;
    }
    /**
     * @return
     */
    public String getGrantType() {
        return grantType;
    }
    /**
     * @param grantType
     * @return
     */
    public BaseClientHttpRequestInterceptorUpdate setGrantType(String grantType) {
        this.grantType = grantType;
        return this;
    }
    /**
     * @return
     */
    public String getScope() {
        return scope;
    }
    /**
     * @param scope
     * @return
     */
    public BaseClientHttpRequestInterceptorUpdate setScope(String scope) {
        this.scope = scope;
        return this;
    }
    /**
     * @return
     */
    public String getCookieName() {
        return cookieName;
    }
    /**
     * @param cookieName
     * @return
     */
    public BaseClientHttpRequestInterceptorUpdate setCookieName(String cookieName) {
        this.cookieName = cookieName;
        return this;
    }
    /**
     * @return
     */
    public String[] getCookiesNames() {
        return cookiesNames;
    }
    /**
     * @param cookiesNames
     * @return
     */
    public BaseClientHttpRequestInterceptorUpdate setCookiesNames(String[] cookiesNames) {
        this.cookiesNames = cookiesNames;
        return this;
    }
    /**
     * @return
     */
    public String[] getCookiesNamesInHeader() {
        return cookiesNamesInHeader;
    }
    /**
     * @param cookiesNamesInHeader
     * @return
     */
    public BaseClientHttpRequestInterceptorUpdate setCookiesNamesInHeader(String[] cookiesNamesInHeader) {
        this.cookiesNamesInHeader = cookiesNamesInHeader;
        return this;
    }

}
