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

package org.flcit.springboot.commons.actuator.resttemplate.update;

import org.flcit.springboot.commons.actuator.resttemplate.HttpClientTracesConfiguration;

/**
 * 
 * @since 
 * @author Florian Lestic
 */
public class RestTemplateUpdate {

    private String url;
    private Boolean simpleClientHttp;
    private Integer connectTimeout;
    private Integer connectionRequestTimeout;
    private Integer socketTimeout;
    private Integer chunkSize;
    private Integer maxConnectionPerRoute;
    private Integer maxConnectionTotal;
    private Boolean proxy;
    private Boolean streaming;
    private Boolean sslCertificateVerification;
    private HttpClientTracesConfiguration traces;
    private String queryParamsListMode;

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
    public RestTemplateUpdate setUrl(String url) {
        this.url = url;
        return this;
    }
    protected Boolean getSimpleClientHttp() {
        return simpleClientHttp;
    }
    /**
     * @param simpleClientHttp
     * @return
     */
    public RestTemplateUpdate setSimpleClientHttp(Boolean simpleClientHttp) {
        this.simpleClientHttp = simpleClientHttp;
        return this;
    }
    protected Integer getConnectTimeout() {
        return connectTimeout;
    }
    /**
     * @param connectTimeout
     * @return
     */
    public RestTemplateUpdate setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }
    protected Integer getConnectionRequestTimeout() {
        return connectionRequestTimeout;
    }
    /**
     * @param connectionRequestTimeout
     * @return
     */
    public RestTemplateUpdate setConnectionRequestTimeout(Integer connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;
        return this;
    }
    protected Integer getSocketTimeout() {
        return socketTimeout;
    }
    /**
     * @param socketTimeout
     * @return
     */
    public RestTemplateUpdate setSocketTimeout(Integer socketTimeout) {
        this.socketTimeout = socketTimeout;
        return this;
    }
    protected Integer getChunkSize() {
        return chunkSize;
    }
    /**
     * @param chunkSize
     * @return
     */
    public RestTemplateUpdate setChunkSize(Integer chunkSize) {
        this.chunkSize = chunkSize;
        return this;
    }
    protected Integer getMaxConnectionPerRoute() {
        return maxConnectionPerRoute;
    }
    /**
     * @param maxConnectionPerRoute
     * @return
     */
    public RestTemplateUpdate setMaxConnectionPerRoute(Integer maxConnectionPerRoute) {
        this.maxConnectionPerRoute = maxConnectionPerRoute;
        return this;
    }
    protected Integer getMaxConnectionTotal() {
        return maxConnectionTotal;
    }
    /**
     * @param maxConnectionTotal
     * @return
     */
    public RestTemplateUpdate setMaxConnectionTotal(Integer maxConnectionTotal) {
        this.maxConnectionTotal = maxConnectionTotal;
        return this;
    }
    /**
     * @return
     */
    public Boolean getProxy() {
        return proxy;
    }
    /**
     * @param proxy
     * @return
     */
    public RestTemplateUpdate setProxy(Boolean proxy) {
        this.proxy = proxy;
        return this;
    }
    protected Boolean getStreaming() {
        return streaming;
    }
    /**
     * @param streaming
     * @return
     */
    public RestTemplateUpdate setStreaming(Boolean streaming) {
        this.streaming = streaming;
        return this;
    }
    protected Boolean getSslCertificateVerification() {
        return sslCertificateVerification;
    }
    /**
     * @param sslCertificateVerification
     * @return
     */
    public RestTemplateUpdate setSslCertificateVerification(Boolean sslCertificateVerification) {
        this.sslCertificateVerification = sslCertificateVerification;
        return this;
    }
    protected HttpClientTracesConfiguration getTraces() {
        return traces;
    }
    /**
     * @param traces
     * @return
     */
    public RestTemplateUpdate setTraces(HttpClientTracesConfiguration traces) {
        this.traces = traces;
        return this;
    }
    /**
     * @return
     */
    public String getQueryParamsListMode() {
        return queryParamsListMode;
    }
    /**
     * @param queryParamsListMode
     * @return
     */
    public RestTemplateUpdate setQueryParamsListMode(String queryParamsListMode) {
        this.queryParamsListMode = queryParamsListMode;
        return this;
    }
}
