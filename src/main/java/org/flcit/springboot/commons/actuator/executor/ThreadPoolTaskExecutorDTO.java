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

package org.flcit.springboot.commons.actuator.executor;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import org.flcit.commons.core.util.ReflectionUtils;

/**
 * 
 * @since 
 * @author Florian Lestic
 */
public class ThreadPoolTaskExecutorDTO extends ConfigurationExecutor {

    private final int activeCount;
    private final int poolSize;
    private final int corePoolSize;
    private final int keepAliveSeconds;
    private final int maxPoolSize;
    private final Integer queueCapacity;
    private final Integer queueSize;
    private final boolean prefersShortLivedTasks;
    private final ThreadPoolExecutorDTO threadPoolExecutor;

    /**
     * @param name
     * @param executor
     */
    public ThreadPoolTaskExecutorDTO(final String name, final ThreadPoolTaskExecutor executor) {
        super(name, executor);
        this.activeCount = executor.getActiveCount();
        this.poolSize = executor.getPoolSize();
        this.corePoolSize = executor.getCorePoolSize();
        this.keepAliveSeconds = executor.getKeepAliveSeconds();
        this.maxPoolSize = executor.getMaxPoolSize();
        this.queueCapacity = (Integer) ReflectionUtils.getSafeMethodValue(executor, "getQueueCapacity");
        this.queueSize = (Integer) ReflectionUtils.getSafeMethodValue(executor, "getQueueSize");
        this.prefersShortLivedTasks = executor.prefersShortLivedTasks();
        this.threadPoolExecutor = new ThreadPoolExecutorDTO(executor.getThreadPoolExecutor());
    }

    /**
     * @return
     */
    public int getActiveCount() {
        return activeCount;
    }

    /**
     * @return
     */
    public int getPoolSize() {
        return poolSize;
    }

    /**
     * @return
     */
    public int getCorePoolSize() {
        return corePoolSize;
    }

    /**
     * @return
     */
    public int getKeepAliveSeconds() {
        return keepAliveSeconds;
    }

    /**
     * @return
     */
    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    /**
     * @return
     */
    public Integer getQueueCapacity() {
        return queueCapacity;
    }

    /**
     * @return
     */
    public Integer getQueueSize() {
        return queueSize;
    }

    /**
     * @return
     */
    public boolean isPrefersShortLivedTasks() {
        return prefersShortLivedTasks;
    }

    /**
     * @return
     */
    public ThreadPoolExecutorDTO getThreadPoolExecutor() {
        return threadPoolExecutor;
    }

}
