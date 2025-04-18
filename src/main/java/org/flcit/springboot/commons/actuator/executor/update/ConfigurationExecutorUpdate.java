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

package org.flcit.springboot.commons.actuator.executor.update;

/**
 * 
 * @since 
 * @author Florian Lestic
 */
public class ConfigurationExecutorUpdate {

    private Integer threadPriority;
    private String threadNamePrefix;
    private Boolean daemon;

    private Boolean waitForTasksToCompleteOnShutdown;
    private Long awaitTerminationMillis;
    private Long defaultTimeout;
    private Long timeout;
    private String beanName;

    private Integer concurrencyLimit;
    private Boolean throttleActive;

    private Integer poolSize;

    /**
     * @return
     */
    public Integer getThreadPriority() {
        return threadPriority;
    }

    /**
     * @param threadPriority
     * @return
     */
    public ConfigurationExecutorUpdate setThreadPriority(Integer threadPriority) {
        this.threadPriority = threadPriority;
        return this;
    }

    /**
     * @return
     */
    public String getThreadNamePrefix() {
        return threadNamePrefix;
    }

    /**
     * @param threadNamePrefix
     * @return
     */
    public ConfigurationExecutorUpdate setThreadNamePrefix(String threadNamePrefix) {
        this.threadNamePrefix = threadNamePrefix;
        return this;
    }

    /**
     * @return
     */
    public Boolean getDaemon() {
        return daemon;
    }

    /**
     * @param daemon
     * @return
     */
    public ConfigurationExecutorUpdate setDaemon(Boolean daemon) {
        this.daemon = daemon;
        return this;
    }

    /**
     * @return
     */
    public Boolean getWaitForTasksToCompleteOnShutdown() {
        return waitForTasksToCompleteOnShutdown;
    }

    /**
     * @param waitForTasksToCompleteOnShutdown
     * @return
     */
    public ConfigurationExecutorUpdate setWaitForTasksToCompleteOnShutdown(Boolean waitForTasksToCompleteOnShutdown) {
        this.waitForTasksToCompleteOnShutdown = waitForTasksToCompleteOnShutdown;
        return this;
    }

    /**
     * @return
     */
    public Long getAwaitTerminationMillis() {
        return awaitTerminationMillis;
    }

    /**
     * @param awaitTerminationMillis
     * @return
     */
    public ConfigurationExecutorUpdate setAwaitTerminationMillis(Long awaitTerminationMillis) {
        this.awaitTerminationMillis = awaitTerminationMillis;
        return this;
    }

    /**
     * @return
     */
    public Long getDefaultTimeout() {
        return defaultTimeout;
    }

    /**
     * @param defaultTimeout
     * @return
     */
    public ConfigurationExecutorUpdate setDefaultTimeout(Long defaultTimeout) {
        this.defaultTimeout = defaultTimeout;
        return this;
    }

    /**
     * @return
     */
    public Long getTimeout() {
        return timeout;
    }

    /**
     * @param timeout
     * @return
     */
    public ConfigurationExecutorUpdate setTimeout(Long timeout) {
        this.timeout = timeout;
        return this;
    }

    /**
     * @return
     */
    public String getBeanName() {
        return beanName;
    }

    /**
     * @param beanName
     * @return
     */
    public ConfigurationExecutorUpdate setBeanName(String beanName) {
        this.beanName = beanName;
        return this;
    }

    /**
     * @return
     */
    public Integer getConcurrencyLimit() {
        return concurrencyLimit;
    }

    /**
     * @param concurrencyLimit
     * @return
     */
    public ConfigurationExecutorUpdate setConcurrencyLimit(Integer concurrencyLimit) {
        this.concurrencyLimit = concurrencyLimit;
        return this;
    }

    /**
     * @return
     */
    public Boolean getThrottleActive() {
        return throttleActive;
    }

    /**
     * @param throttleActive
     * @return
     */
    public ConfigurationExecutorUpdate setThrottleActive(Boolean throttleActive) {
        this.throttleActive = throttleActive;
        return this;
    }

    /**
     * @return
     */
    public Integer getPoolSize() {
        return poolSize;
    }

    /**
     * @param poolSize
     * @return
     */
    public ConfigurationExecutorUpdate setPoolSize(Integer poolSize) {
        this.poolSize = poolSize;
        return this;
    }

}
