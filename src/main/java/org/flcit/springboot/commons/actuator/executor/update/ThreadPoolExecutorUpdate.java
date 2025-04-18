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
public class ThreadPoolExecutorUpdate {

    private Integer corePoolSize;
    private Integer keepAliveSeconds;
    private Integer maxPoolSize;
    private Integer queueCapacity;
    private Boolean allowsCoreThreadTimeOut;

    private Boolean removeOnCancelPolicy;
    private Boolean continueExistingPeriodicTasksAfterShutdownPolicy;
    private Boolean executeExistingDelayedTasksAfterShutdownPolicy;

    /**
     * @return
     */
    public Integer getCorePoolSize() {
        return corePoolSize;
    }

    /**
     * @param corePoolSize
     * @return
     */
    public ThreadPoolExecutorUpdate setCorePoolSize(Integer corePoolSize) {
        this.corePoolSize = corePoolSize;
        return this;
    }

    /**
     * @return
     */
    public Integer getKeepAliveSeconds() {
        return keepAliveSeconds;
    }

    /**
     * @param keepAliveSeconds
     * @return
     */
    public ThreadPoolExecutorUpdate setKeepAliveSeconds(Integer keepAliveSeconds) {
        this.keepAliveSeconds = keepAliveSeconds;
        return this;
    }

    /**
     * @return
     */
    public Integer getMaxPoolSize() {
        return maxPoolSize;
    }

    /**
     * @return
     */
    public Integer getMaximumPoolSize() {
        return getMaxPoolSize();
    }

    /**
     * @param maxPoolSize
     * @return
     */
    public ThreadPoolExecutorUpdate setMaxPoolSize(Integer maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
        return this;
    }

    /**
     * @return
     */
    public Integer getQueueCapacity() {
        return queueCapacity;
    }

    /**
     * @param queueCapacity
     * @return
     */
    public ThreadPoolExecutorUpdate setQueueCapacity(Integer queueCapacity) {
        this.queueCapacity = queueCapacity;
        return this;
    }

    /**
     * @return
     */
    public Boolean getAllowsCoreThreadTimeOut() {
        return allowsCoreThreadTimeOut;
    }

    /**
     * @param allowsCoreThreadTimeOut
     * @return
     */
    public ThreadPoolExecutorUpdate setAllowsCoreThreadTimeOut(Boolean allowsCoreThreadTimeOut) {
        this.allowsCoreThreadTimeOut = allowsCoreThreadTimeOut;
        return this;
    }

    /**
     * @return
     */
    public Boolean getRemoveOnCancelPolicy() {
        return removeOnCancelPolicy;
    }

    /**
     * @param removeOnCancelPolicy
     * @return
     */
    public ThreadPoolExecutorUpdate setRemoveOnCancelPolicy(Boolean removeOnCancelPolicy) {
        this.removeOnCancelPolicy = removeOnCancelPolicy;
        return this;
    }

    /**
     * @return
     */
    public Boolean getContinueExistingPeriodicTasksAfterShutdownPolicy() {
        return continueExistingPeriodicTasksAfterShutdownPolicy;
    }

    /**
     * @param continueExistingPeriodicTasksAfterShutdownPolicy
     * @return
     */
    public ThreadPoolExecutorUpdate setContinueExistingPeriodicTasksAfterShutdownPolicy(
            Boolean continueExistingPeriodicTasksAfterShutdownPolicy) {
        this.continueExistingPeriodicTasksAfterShutdownPolicy = continueExistingPeriodicTasksAfterShutdownPolicy;
        return this;
    }

    /**
     * @return
     */
    public Boolean getExecuteExistingDelayedTasksAfterShutdownPolicy() {
        return executeExistingDelayedTasksAfterShutdownPolicy;
    }

    /**
     * @param executeExistingDelayedTasksAfterShutdownPolicy
     * @return
     */
    public ThreadPoolExecutorUpdate setExecuteExistingDelayedTasksAfterShutdownPolicy(Boolean executeExistingDelayedTasksAfterShutdownPolicy) {
        this.executeExistingDelayedTasksAfterShutdownPolicy = executeExistingDelayedTasksAfterShutdownPolicy;
        return this;
    }

}
