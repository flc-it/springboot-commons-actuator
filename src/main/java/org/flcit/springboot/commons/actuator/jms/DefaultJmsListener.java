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

package org.flcit.springboot.commons.actuator.jms;

import java.util.concurrent.Executor;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.ExceptionListener;

import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.util.ErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

import org.flcit.springboot.commons.actuator.executor.BaseExecutor;
import org.flcit.springboot.commons.actuator.util.CommonsActuatorUtils;
import org.flcit.commons.core.util.ClassUtils;
import org.flcit.commons.core.util.ReflectionUtils;

/**
 * 
 * @since 
 * @author Florian Lestic
 */
public class DefaultJmsListener extends BaseJmsListener {

    private final Destination destination;
    private final String destinationName;
    private final String clientId;
    private final String durableSubscriptionName;
    private final String messageSelector;
    private final String subscriptionName;
    private final Class<? extends ConnectionFactory> connectionFactory;
    private final Class<? extends ErrorHandler> errorHandler;
    private final Class<? extends ExceptionListener> exceptionListener;
    private final int activeConsumerCount;
    private final int cacheLevel;
    private final int concurrentConsumers;
    private final int idleConsumerLimit;
    private final Integer idleReceivesPerTaskLimit;
    private final int idleTaskExecutionLimit;
    private final int maxConcurrentConsumers;
    private final int maxMessagesPerTask;
    private final int pausedTaskCount;
    private final int scheduledConsumerCount;
    private final int sessionAcknowledgeMode;
    private final boolean acceptMessagesWhileStopping;
    private final boolean active;
    private final boolean exposeListenerSession;
    private final boolean pubSubNoLocal;
    private final boolean recovering;
    private final boolean registeredWithDestination;
    private final boolean sessionTransacted;
    private final boolean subscriptionDurable;
    private final boolean subscriptionShared;
    private final FixedBackOffDTO backOff;
    private final BaseExecutor taskExecutor;

    /**
     * @param id
     * @param listener
     */
    public DefaultJmsListener(final String id, final DefaultMessageListenerContainer listener) {
        super(id, listener);
        this.destination = listener.getDestination();
        this.destinationName = listener.getDestinationName();
        this.clientId = listener.getClientId();
        this.durableSubscriptionName = listener.getDurableSubscriptionName();
        this.messageSelector = listener.getMessageSelector();
        this.subscriptionName = listener.getSubscriptionName();
        this.connectionFactory = ClassUtils.getSafe(listener.getConnectionFactory());
        this.errorHandler = ClassUtils.getSafe(listener.getErrorHandler());
        this.exceptionListener = ClassUtils.getSafe(listener.getExceptionListener());
        this.activeConsumerCount = listener.getActiveConsumerCount();
        this.cacheLevel = listener.getCacheLevel();
        this.concurrentConsumers = listener.getConcurrentConsumers();
        this.idleConsumerLimit = listener.getIdleConsumerLimit();
        this.idleReceivesPerTaskLimit = (Integer) ReflectionUtils.getSafeMethodValue(listener, "getIdleReceivesPerTaskLimit");
        this.idleTaskExecutionLimit = listener.getIdleTaskExecutionLimit();
        this.maxConcurrentConsumers = listener.getMaxConcurrentConsumers();
        this.maxMessagesPerTask = listener.getMaxMessagesPerTask();
        this.pausedTaskCount = listener.getPausedTaskCount();
        this.scheduledConsumerCount = listener.getScheduledConsumerCount();
        this.sessionAcknowledgeMode = listener.getSessionAcknowledgeMode();
        this.acceptMessagesWhileStopping = listener.isAcceptMessagesWhileStopping();
        this.active = listener.isActive();
        this.exposeListenerSession = listener.isExposeListenerSession();
        this.pubSubNoLocal = listener.isPubSubNoLocal();
        this.recovering = listener.isRecovering();
        this.registeredWithDestination = listener.isRegisteredWithDestination();
        this.sessionTransacted = listener.isSessionTransacted();
        this.subscriptionDurable = listener.isSubscriptionDurable();
        this.subscriptionShared = listener.isSubscriptionShared();
        this.backOff = new FixedBackOffDTO((FixedBackOff) CommonsActuatorUtils.getBackOff(listener));
        this.taskExecutor = CommonsActuatorUtils.convert(ReflectionUtils.getFieldValue(listener, "taskExecutor", Executor.class));
    }

    /**
     * @return
     */
    public Destination getDestination() {
        return destination;
    }

    /**
     * @return
     */
    public String getDestinationName() {
        return destinationName;
    }

    /**
     * @return
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * @return
     */
    public String getDurableSubscriptionName() {
        return durableSubscriptionName;
    }

    /**
     * @return
     */
    public String getMessageSelector() {
        return messageSelector;
    }

    /**
     * @return
     */
    public String getSubscriptionName() {
        return subscriptionName;
    }

    /**
     * @return
     */
    public Class<? extends ConnectionFactory> getConnectionFactory() {
        return connectionFactory;
    }

    /**
     * @return
     */
    public Class<? extends ErrorHandler> getErrorHandler() {
        return errorHandler;
    }

    /**
     * @return
     */
    public Class<? extends ExceptionListener> getExceptionListener() {
        return exceptionListener;
    }

    /**
     * @return
     */
    public int getActiveConsumerCount() {
        return activeConsumerCount;
    }

    /**
     * @return
     */
    public int getCacheLevel() {
        return cacheLevel;
    }

    /**
     * @return
     */
    public int getConcurrentConsumers() {
        return concurrentConsumers;
    }

    /**
     * @return
     */
    public int getIdleConsumerLimit() {
        return idleConsumerLimit;
    }

    /**
     * @return
     */
    public Integer getIdleReceivesPerTaskLimit() {
        return idleReceivesPerTaskLimit;
    }

    /**
     * @return
     */
    public int getIdleTaskExecutionLimit() {
        return idleTaskExecutionLimit;
    }

    /**
     * @return
     */
    public int getMaxConcurrentConsumers() {
        return maxConcurrentConsumers;
    }

    /**
     * @return
     */
    public int getMaxMessagesPerTask() {
        return maxMessagesPerTask;
    }

    /**
     * @return
     */
    public int getPausedTaskCount() {
        return pausedTaskCount;
    }

    /**
     * @return
     */
    public int getScheduledConsumerCount() {
        return scheduledConsumerCount;
    }

    /**
     * @return
     */
    public int getSessionAcknowledgeMode() {
        return sessionAcknowledgeMode;
    }

    /**
     * @return
     */
    public boolean isAcceptMessagesWhileStopping() {
        return acceptMessagesWhileStopping;
    }

    /**
     * @return
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @return
     */
    public boolean isExposeListenerSession() {
        return exposeListenerSession;
    }

    /**
     * @return
     */
    public boolean isPubSubNoLocal() {
        return pubSubNoLocal;
    }

    /**
     * @return
     */
    public boolean isRecovering() {
        return recovering;
    }

    /**
     * @return
     */
    public boolean isRegisteredWithDestination() {
        return registeredWithDestination;
    }

    /**
     * @return
     */
    public boolean isSessionTransacted() {
        return sessionTransacted;
    }

    /**
     * @return
     */
    public boolean isSubscriptionDurable() {
        return subscriptionDurable;
    }

    /**
     * @return
     */
    public boolean isSubscriptionShared() {
        return subscriptionShared;
    }

    /**
     * @return
     */
    public FixedBackOffDTO getBackOff() {
        return backOff;
    }

    /**
     * @return
     */
    public BaseExecutor getTaskExecutor() {
        return taskExecutor;
    }

}
