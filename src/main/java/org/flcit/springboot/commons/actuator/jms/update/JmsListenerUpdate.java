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

package org.flcit.springboot.commons.actuator.jms.update;

/**
 * 
 * @since 
 * @author Florian Lestic
 */
public class JmsListenerUpdate {

    private Boolean acceptMessagesWhileStopping;
    private String clientId;
    private String concurrency;
    private Integer concurrentConsumers;
    private String destinationName;
    private String durableSubscriptionName;
    private Integer idleConsumerLimit;
    private Integer idleReceivesPerTaskLimit;
    private Integer idleTaskExecutionLimit;
    private Integer maxConcurrentConsumers;
    private Integer maxMessagesPerTask;
    private String messageSelector;
    private Boolean pubSubDomain;
    private Boolean pubSubNoLocal;
    private Long receiveTimeout;
    private Long recoveryInterval;
    private Boolean replyPubSubDomain;
    private Integer sessionAcknowledgeMode;
    private String sessionAcknowledgeModeName;
    private Boolean sessionTransacted;
    private Boolean subscriptionDurable;
    private String subscriptionName;
    private String subscriptionShared;
    private Integer transactionTimeout;
    private Integer cacheLevel;
    private String cacheLevelName;

    /**
     * @return
     */
    public Boolean getAcceptMessagesWhileStopping() {
        return acceptMessagesWhileStopping;
    }
    /**
     * @param acceptMessagesWhileStopping
     * @return
     */
    public JmsListenerUpdate setAcceptMessagesWhileStopping(Boolean acceptMessagesWhileStopping) {
        this.acceptMessagesWhileStopping = acceptMessagesWhileStopping;
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
    public JmsListenerUpdate setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }
    /**
     * @return
     */
    public String getConcurrency() {
        return concurrency;
    }
    /**
     * @param concurrency
     * @return
     */
    public JmsListenerUpdate setConcurrency(String concurrency) {
        this.concurrency = concurrency;
        return this;
    }
    /**
     * @return
     */
    public Integer getConcurrentConsumers() {
        return concurrentConsumers;
    }
    /**
     * @param concurrentConsumers
     * @return
     */
    public JmsListenerUpdate setConcurrentConsumers(Integer concurrentConsumers) {
        this.concurrentConsumers = concurrentConsumers;
        return this;
    }
    /**
     * @return
     */
    public String getDestinationName() {
        return destinationName;
    }
    /**
     * @param destinationName
     * @return
     */
    public JmsListenerUpdate setDestinationName(String destinationName) {
        this.destinationName = destinationName;
        return this;
    }
    /**
     * @return
     */
    public String getDurableSubscriptionName() {
        return durableSubscriptionName;
    }
    /**
     * @param durableSubscriptionName
     * @return
     */
    public JmsListenerUpdate setDurableSubscriptionName(String durableSubscriptionName) {
        this.durableSubscriptionName = durableSubscriptionName;
        return this;
    }
    /**
     * @return
     */
    public Integer getIdleConsumerLimit() {
        return idleConsumerLimit;
    }
    /**
     * @param idleConsumerLimit
     * @return
     */
    public JmsListenerUpdate setIdleConsumerLimit(Integer idleConsumerLimit) {
        this.idleConsumerLimit = idleConsumerLimit;
        return this;
    }
    /**
     * @return
     */
    public Integer getIdleReceivesPerTaskLimit() {
        return idleReceivesPerTaskLimit;
    }
    /**
     * @param idleReceivesPerTaskLimit
     * @return
     */
    public JmsListenerUpdate setIdleReceivesPerTaskLimit(Integer idleReceivesPerTaskLimit) {
        this.idleReceivesPerTaskLimit = idleReceivesPerTaskLimit;
        return this;
    }
    /**
     * @return
     */
    public Integer getIdleTaskExecutionLimit() {
        return idleTaskExecutionLimit;
    }
    /**
     * @param idleTaskExecutionLimit
     * @return
     */
    public JmsListenerUpdate setIdleTaskExecutionLimit(Integer idleTaskExecutionLimit) {
        this.idleTaskExecutionLimit = idleTaskExecutionLimit;
        return this;
    }
    /**
     * @return
     */
    public Integer getMaxConcurrentConsumers() {
        return maxConcurrentConsumers;
    }
    /**
     * @param maxConcurrentConsumers
     * @return
     */
    public JmsListenerUpdate setMaxConcurrentConsumers(Integer maxConcurrentConsumers) {
        this.maxConcurrentConsumers = maxConcurrentConsumers;
        return this;
    }
    /**
     * @return
     */
    public Integer getMaxMessagesPerTask() {
        return maxMessagesPerTask;
    }
    /**
     * @param maxMessagesPerTask
     * @return
     */
    public JmsListenerUpdate setMaxMessagesPerTask(Integer maxMessagesPerTask) {
        this.maxMessagesPerTask = maxMessagesPerTask;
        return this;
    }
    /**
     * @return
     */
    public String getMessageSelector() {
        return messageSelector;
    }
    /**
     * @param messageSelector
     * @return
     */
    public JmsListenerUpdate setMessageSelector(String messageSelector) {
        this.messageSelector = messageSelector;
        return this;
    }
    /**
     * @return
     */
    public Boolean getPubSubDomain() {
        return pubSubDomain;
    }
    /**
     * @param pubSubDomain
     * @return
     */
    public JmsListenerUpdate setPubSubDomain(Boolean pubSubDomain) {
        this.pubSubDomain = pubSubDomain;
        return this;
    }
    /**
     * @return
     */
    public Boolean getPubSubNoLocal() {
        return pubSubNoLocal;
    }
    /**
     * @param pubSubNoLocal
     * @return
     */
    public JmsListenerUpdate setPubSubNoLocal(Boolean pubSubNoLocal) {
        this.pubSubNoLocal = pubSubNoLocal;
        return this;
    }
    /**
     * @return
     */
    public Long getReceiveTimeout() {
        return receiveTimeout;
    }
    /**
     * @param receiveTimeout
     * @return
     */
    public JmsListenerUpdate setReceiveTimeout(Long receiveTimeout) {
        this.receiveTimeout = receiveTimeout;
        return this;
    }
    /**
     * @return
     */
    public Long getRecoveryInterval() {
        return recoveryInterval;
    }
    /**
     * @param recoveryInterval
     * @return
     */
    public JmsListenerUpdate setRecoveryInterval(Long recoveryInterval) {
        this.recoveryInterval = recoveryInterval;
        return this;
    }
    /**
     * @return
     */
    public Boolean getReplyPubSubDomain() {
        return replyPubSubDomain;
    }
    /**
     * @param replyPubSubDomain
     * @return
     */
    public JmsListenerUpdate setReplyPubSubDomain(Boolean replyPubSubDomain) {
        this.replyPubSubDomain = replyPubSubDomain;
        return this;
    }
    /**
     * @return
     */
    public Integer getSessionAcknowledgeMode() {
        return sessionAcknowledgeMode;
    }
    /**
     * @param sessionAcknowledgeMode
     * @return
     */
    public JmsListenerUpdate setSessionAcknowledgeMode(Integer sessionAcknowledgeMode) {
        this.sessionAcknowledgeMode = sessionAcknowledgeMode;
        return this;
    }
    /**
     * @return
     */
    public String getSessionAcknowledgeModeName() {
        return sessionAcknowledgeModeName;
    }
    /**
     * @param sessionAcknowledgeModeName
     * @return
     */
    public JmsListenerUpdate setSessionAcknowledgeModeName(String sessionAcknowledgeModeName) {
        this.sessionAcknowledgeModeName = sessionAcknowledgeModeName;
        return this;
    }
    /**
     * @return
     */
    public Boolean getSessionTransacted() {
        return sessionTransacted;
    }
    /**
     * @param sessionTransacted
     * @return
     */
    public JmsListenerUpdate setSessionTransacted(Boolean sessionTransacted) {
        this.sessionTransacted = sessionTransacted;
        return this;
    }
    /**
     * @return
     */
    public Boolean getSubscriptionDurable() {
        return subscriptionDurable;
    }
    /**
     * @param subscriptionDurable
     * @return
     */
    public JmsListenerUpdate setSubscriptionDurable(Boolean subscriptionDurable) {
        this.subscriptionDurable = subscriptionDurable;
        return this;
    }
    /**
     * @return
     */
    public String getSubscriptionName() {
        return subscriptionName;
    }
    /**
     * @param subscriptionName
     * @return
     */
    public JmsListenerUpdate setSubscriptionName(String subscriptionName) {
        this.subscriptionName = subscriptionName;
        return this;
    }
    /**
     * @return
     */
    public String getSubscriptionShared() {
        return subscriptionShared;
    }
    /**
     * @param subscriptionShared
     * @return
     */
    public JmsListenerUpdate setSubscriptionShared(String subscriptionShared) {
        this.subscriptionShared = subscriptionShared;
        return this;
    }
    /**
     * @return
     */
    public Integer getTransactionTimeout() {
        return transactionTimeout;
    }

    /**
     * @param transactionTimeout
     * @return
     */
    public JmsListenerUpdate setTransactionTimeout(Integer transactionTimeout) {
        this.transactionTimeout = transactionTimeout;
        return this;
    }

    /**
     * @return
     */
    public Integer getCacheLevel() {
        return cacheLevel;
    }

    /**
     * @param cacheLevel
     * @return
     */
    public JmsListenerUpdate setCacheLevel(Integer cacheLevel) {
        this.cacheLevel = cacheLevel;
        return this;
    }

    /**
     * @return
     */
    public String getCacheLevelName() {
        return cacheLevelName;
    }

    /**
     * @param cacheLevelName
     * @return
     */
    public JmsListenerUpdate setCacheLevelName(String cacheLevelName) {
        this.cacheLevelName = cacheLevelName;
        return this;
    }

    /**
     * @return
     */
    public boolean needRestart() {
        return clientId != null
                || destinationName != null
                || durableSubscriptionName != null
                || messageSelector != null
                || pubSubDomain != null
                || subscriptionDurable != null
                || subscriptionName != null
                || subscriptionShared != null;
    }

}
