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
import java.util.Set;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.jms.config.JmsListenerEndpointRegistry;
import org.springframework.jms.listener.MessageListenerContainer;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import org.flcit.springboot.commons.actuator.jms.BaseJmsListener;
import org.flcit.springboot.commons.actuator.jms.FixedBackOffDTO;
import org.flcit.springboot.commons.actuator.jms.update.JmsListenerAction;
import org.flcit.springboot.commons.actuator.jms.update.JmsListenerUpdate;
import org.flcit.springboot.commons.actuator.util.CommonsActuatorUtils;
import org.flcit.springboot.commons.core.util.BeanUtils;

/**
 * 
 * @since 
 * @author Florian Lestic
 */
@Endpoint(id = "jmslisteners")
public class JmsListenersEndpoint {

    private final JmsListenerEndpointRegistry registry;

    /**
     * @param registry
     */
    public JmsListenersEndpoint(JmsListenerEndpointRegistry registry) {
        this.registry = registry;
    }

    /**
     * @return
     */
    @ReadOperation
    public Map<String, BaseJmsListener> listeners() {
        final Set<String> ids = registry.getListenerContainerIds();
        Map<String, BaseJmsListener> listeners = new HashMap<>(ids.size());
        for (String id: ids) {
            listeners.put(id, getConvert(id));
        }
        return listeners;
    }

    private BaseJmsListener getConvert(final String id) {
        return CommonsActuatorUtils.convert(id, registry.getListenerContainer(id));
    }

    /**
     * @param id
     * @return
     */
    @ReadOperation
    public BaseJmsListener get(@Selector String id) {
        return getConvert(id);
    }

    /**
     * @param action
     */
    @WriteOperation
    public void action(@Selector JmsListenerAction action) {
        for (String id : registry.getListenerContainerIds()) {
            action(id, action);
        }
    }

    /**
     * @param id
     * @param action
     */
    @WriteOperation
    public void action(@Selector String id, @Selector JmsListenerAction action) {
        final MessageListenerContainer listener = registry.getListenerContainer(id);
        Assert.notNull(listener, "Listener must not be null");
        if (action == JmsListenerAction.start) {
            start(listener);
        } else if (action == JmsListenerAction.stop) {
            stop(listener);
        } else if (action == JmsListenerAction.restart) {
            restart(listener);
        }
    }

    private static final void start(final MessageListenerContainer listener) {
        if (!listener.isRunning()) {
            listener.start();
        }
    }

    private static final void stop(final MessageListenerContainer listener) {
        if (listener.isRunning()) {
            listener.stop();
        }
    }

    private static final void restart(final MessageListenerContainer listener) {
        stop(listener);
        start(listener);
    }

    /**
     * @param id
     * @param acceptMessagesWhileStopping
     * @param clientId
     * @param concurrency
     * @param concurrentConsumers
     * @param destinationName
     * @param durableSubscriptionName
     * @param idleConsumerLimit
     * @param idleReceivesPerTaskLimit
     * @param idleTaskExecutionLimit
     * @param maxConcurrentConsumers
     * @param maxMessagesPerTask
     * @param messageSelector
     * @param pubSubDomain
     * @param pubSubNoLocal
     * @param receiveTimeout
     * @param recoveryInterval
     * @param replyPubSubDomain
     * @param sessionAcknowledgeMode
     * @param sessionAcknowledgeModeName
     * @param sessionTransacted
     * @param subscriptionDurable
     * @param subscriptionName
     * @param subscriptionShared
     * @param transactionTimeout
     * @param cacheLevel
     * @param cacheLevelName
     * @param backOffInterval
     * @param backOffMaxAttempts
     */
    @SuppressWarnings("java:S107")
    @WriteOperation
    public void update(@Selector String id,
            @Nullable Boolean acceptMessagesWhileStopping,
            @Nullable String clientId,
            @Nullable String concurrency,
            @Nullable Integer concurrentConsumers,
            @Nullable String destinationName,
            @Nullable String durableSubscriptionName,
            @Nullable Integer idleConsumerLimit,
            @Nullable Integer idleReceivesPerTaskLimit,
            @Nullable Integer idleTaskExecutionLimit,
            @Nullable Integer maxConcurrentConsumers,
            @Nullable Integer maxMessagesPerTask,
            @Nullable String messageSelector,
            @Nullable Boolean pubSubDomain,
            @Nullable Boolean pubSubNoLocal,
            @Nullable Long receiveTimeout,
            @Nullable Long recoveryInterval,
            @Nullable Boolean replyPubSubDomain,
            @Nullable Integer sessionAcknowledgeMode,
            @Nullable String sessionAcknowledgeModeName,
            @Nullable Boolean sessionTransacted,
            @Nullable Boolean subscriptionDurable,
            @Nullable String subscriptionName,
            @Nullable String subscriptionShared,
            @Nullable Integer transactionTimeout,
            @Nullable Integer cacheLevel,
            @Nullable String cacheLevelName,
            @Nullable Long backOffInterval,
            @Nullable Long backOffMaxAttempts) {
        final MessageListenerContainer listener = registry.getListenerContainer(id);
        BeanUtils.copyNonNullProperties(
                new FixedBackOffDTO(backOffInterval, backOffMaxAttempts),
                CommonsActuatorUtils.getBackOff(listener)
        );
        final JmsListenerUpdate listenerUpdate = new JmsListenerUpdate()
                .setAcceptMessagesWhileStopping(acceptMessagesWhileStopping)
                .setCacheLevel(cacheLevel)
                .setCacheLevelName(cacheLevelName)
                .setClientId(clientId)
                .setConcurrency(concurrency)
                .setConcurrentConsumers(concurrentConsumers)
                .setDestinationName(destinationName)
                .setDurableSubscriptionName(durableSubscriptionName)
                .setIdleConsumerLimit(idleConsumerLimit)
                .setIdleReceivesPerTaskLimit(idleReceivesPerTaskLimit)
                .setIdleTaskExecutionLimit(idleTaskExecutionLimit)
                .setMaxConcurrentConsumers(maxConcurrentConsumers)
                .setMaxMessagesPerTask(maxMessagesPerTask)
                .setMessageSelector(messageSelector)
                .setPubSubDomain(replyPubSubDomain)
                .setPubSubNoLocal(pubSubNoLocal)
                .setReceiveTimeout(receiveTimeout)
                .setRecoveryInterval(recoveryInterval)
                .setReplyPubSubDomain(replyPubSubDomain)
                .setSessionAcknowledgeMode(sessionAcknowledgeMode)
                .setSessionAcknowledgeModeName(sessionAcknowledgeModeName)
                .setSessionTransacted(sessionTransacted)
                .setSubscriptionDurable(subscriptionDurable)
                .setSubscriptionName(subscriptionName)
                .setSubscriptionShared(subscriptionShared)
                .setTransactionTimeout(transactionTimeout);
        BeanUtils.copyNonNullProperties(
                listenerUpdate,
                listener
        );
        if (listenerUpdate.needRestart()) {
            restart(listener);
        }
    }

}
