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

import java.util.Map;
import java.util.Map.Entry;

import javax.security.auth.RefreshFailedException;
import javax.security.auth.Refreshable;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.lang.Nullable;

import org.flcit.springboot.commons.actuator.clienthttprequestinterceptor.BaseClientHttpRequestInterceptor;
import org.flcit.springboot.commons.actuator.clienthttprequestinterceptor.update.BaseClientHttpRequestInterceptorUpdate;
import org.flcit.springboot.commons.actuator.clienthttprequestinterceptor.update.ClientHttpRequestInterceptorAction;
import org.flcit.springboot.commons.actuator.util.CommonsActuatorUtils;
import org.flcit.springboot.commons.core.util.BeanUtils;

/**
 * 
 * @since 
 * @author Florian Lestic
 */
@Endpoint(id = "clienthttprequestinterceptors")
public class ClientHttpRequestInterceptorsEndpoint extends AbstractBeansEndpoint<ClientHttpRequestInterceptor, BaseClientHttpRequestInterceptor> {

    /**
     * @param context
     */
    public ClientHttpRequestInterceptorsEndpoint(ConfigurableApplicationContext context) {
        super(context);
    }

    /**
     *
     */
    @Override
    @ReadOperation
    public Map<String, BaseClientHttpRequestInterceptor> list() {
        return super.list();
    }

    /**
     *
     */
    @Override
    @ReadOperation
    public BaseClientHttpRequestInterceptor get(@Selector String name) {
        return super.get(name);
    }

    @Override
    BaseClientHttpRequestInterceptor convert(String name, ClientHttpRequestInterceptor value) {
        return CommonsActuatorUtils.convert(name, value);
    }

    /**
     * @param action
     */
    @WriteOperation
    public void action(@Selector ClientHttpRequestInterceptorAction action) {
        for (Entry<String, ClientHttpRequestInterceptor> entry: listBeans().entrySet()) {
            action(entry.getValue(), action);
        }
    }

    /**
     * @param name
     * @param action
     */
    @WriteOperation
    public void action(@Selector String name, @Selector ClientHttpRequestInterceptorAction action) {
        action(getBean(name), action);
    }

    private void action(final ClientHttpRequestInterceptor interceptor, final ClientHttpRequestInterceptorAction action) {
        if (interceptor == null) {
            return;
        }
        if (action == ClientHttpRequestInterceptorAction.refresh) {
            refresh(interceptor);
        }
    }

    /**
     * @param interceptor
     */
    public static final void refresh(final ClientHttpRequestInterceptor interceptor) {
        if (interceptor instanceof Refreshable) {
            try {
                ((Refreshable) interceptor).refresh();
            } catch (RefreshFailedException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    /**
     * @param name
     * @param service
     * @param url
     * @param username
     * @param password
     * @param clientId
     * @param clientSecret
     * @param grantType
     * @param scope
     * @param cookieName
     * @param cookiesNames
     * @param cookiesNamesInHeader
     * @throws Exception
     */
    @SuppressWarnings("java:S107")
    @WriteOperation
    public void update(@Selector String name,
            @Nullable String service,
            @Nullable String url,
            @Nullable String username,
            @Nullable String password,
            @Nullable String clientId,
            @Nullable String clientSecret,
            @Nullable String grantType,
            @Nullable String scope,
            @Nullable String cookieName,
            @Nullable String[] cookiesNames,
            @Nullable String[] cookiesNamesInHeader) throws Exception {
        final ClientHttpRequestInterceptor interceptor = getBean(name);
        BeanUtils.copyNonNullProperties(
                new BaseClientHttpRequestInterceptorUpdate()
                .setService(service)
                .setUrl(url)
                .setUsername(username)
                .setPassword(password)
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .setGrantType(grantType)
                .setScope(scope)
                .setCookieName(cookieName)
                .setCookiesNames(cookiesNamesInHeader)
                .setCookiesNamesInHeader(cookiesNamesInHeader),
                interceptor);
        CommonsActuatorUtils.setFieldEnum(interceptor, "grantType", grantType);
        BeanUtils.initializing(interceptor);
        refresh(interceptor);
    }

}
