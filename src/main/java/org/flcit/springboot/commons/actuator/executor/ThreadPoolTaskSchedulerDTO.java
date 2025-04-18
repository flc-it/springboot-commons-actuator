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

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.util.ErrorHandler;

import org.flcit.commons.core.util.ClassUtils;
import org.flcit.commons.core.util.ReflectionUtils;

/**
 * 
 * @since 
 * @author Florian Lestic
 */
public class ThreadPoolTaskSchedulerDTO extends ConfigurationExecutor {

    private final int activeCount;
    private final int poolSize;
    private final boolean prefersShortLivedTasks;
    private final ScheduledThreadPoolExecutorDTO scheduledExecutor;
    private final Class<? extends ErrorHandler> errorHandler;

    /**
     * @param name
     * @param executor
     */
    public ThreadPoolTaskSchedulerDTO(final String name, final ThreadPoolTaskScheduler executor) {
        super(name, executor);
        this.activeCount = executor.getActiveCount();
        this.poolSize = executor.getPoolSize();
        this.prefersShortLivedTasks = executor.prefersShortLivedTasks();
        this.scheduledExecutor = new ScheduledThreadPoolExecutorDTO(executor.getScheduledThreadPoolExecutor());
        this.errorHandler = ClassUtils.getSafe(ReflectionUtils.getFieldValue(executor, "errorHandler", ErrorHandler.class));
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
    public boolean isPrefersShortLivedTasks() {
        return prefersShortLivedTasks;
    }

    /**
     * @return
     */
    public ThreadPoolExecutorDTO getScheduledExecutor() {
        return scheduledExecutor;
    }

    /**
     * @return
     */
    public Class<? extends ErrorHandler> getErrorHandler() {
        return errorHandler;
    }

}
