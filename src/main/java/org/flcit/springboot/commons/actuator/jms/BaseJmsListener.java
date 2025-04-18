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

import org.springframework.jms.listener.MessageListenerContainer;
import org.springframework.jms.support.QosSettings;
import org.springframework.jms.support.converter.MessageConverter;

import org.flcit.commons.core.util.ClassUtils;

/**
 * 
 * @since 
 * @author Florian Lestic
 */
public class BaseJmsListener {

    private final String id;
    private final boolean running;
    private final boolean autoStartup;
    private final boolean pubSubDomain;
    private final boolean replyPubSubDomain;
    private final int phase;
    private final Class<? extends MessageConverter> messageConverter;
    private final QosSettings replyQosSettings;

    /**
     * @param id
     * @param listener
     */
    public BaseJmsListener(final String id, final MessageListenerContainer listener) {
        this.id = id;
        this.running = listener.isRunning();
        this.autoStartup = listener.isAutoStartup();
        this.pubSubDomain = listener.isPubSubDomain();
        this.replyPubSubDomain = listener.isReplyPubSubDomain();
        this.phase = listener.getPhase();
        this.messageConverter = ClassUtils.getSafe(listener.getMessageConverter());
        this.replyQosSettings = listener.getReplyQosSettings();
    }

    /**
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * @return
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * @return
     */
    public boolean isAutoStartup() {
        return autoStartup;
    }

    /**
     * @return
     */
    public boolean isPubSubDomain() {
        return pubSubDomain;
    }

    /**
     * @return
     */
    public boolean isReplyPubSubDomain() {
        return replyPubSubDomain;
    }

    /**
     * @return
     */
    public int getPhase() {
        return phase;
    }

    /**
     * @return
     */
    public Class<? extends MessageConverter> getMessageConverter() {
        return messageConverter;
    }

    /**
     * @return
     */
    public QosSettings getReplyQosSettings() {
        return replyQosSettings;
    }

}
