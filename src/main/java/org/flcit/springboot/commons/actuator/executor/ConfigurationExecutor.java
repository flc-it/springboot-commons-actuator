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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;

import org.springframework.scheduling.concurrent.ExecutorConfigurationSupport;

import org.flcit.commons.core.util.ClassUtils;
import org.flcit.commons.core.util.ObjectUtils;
import org.flcit.commons.core.util.ReflectionUtils;

/**
 * 
 * @since 
 * @author Florian Lestic
 */
public class ConfigurationExecutor extends BaseExecutor {

    private final Class<? extends RejectedExecutionHandler> rejectedExecutionHandler;
    private final boolean waitForTasksToCompleteOnShutdown;
    private final long awaitTerminationMillis;
    private final boolean shutdown;
    private final boolean terminated;
    private final Long defaultTimeout;
    private final Long timeout;

    ConfigurationExecutor(final String name, final ExecutorConfigurationSupport executor) {
        super(ObjectUtils.getOrDefault(name, () -> ReflectionUtils.getFieldValue(executor, "beanName", String.class)), executor);
        this.rejectedExecutionHandler = ClassUtils.getSafe(ReflectionUtils.getFieldValue(executor, "rejectedExecutionHandler", RejectedExecutionHandler.class));
        this.waitForTasksToCompleteOnShutdown = ReflectionUtils.getFieldValue(executor, "waitForTasksToCompleteOnShutdown", boolean.class);
        this.awaitTerminationMillis = ReflectionUtils.getFieldValue(executor, "awaitTerminationMillis", long.class);
        this.shutdown = ReflectionUtils.getFieldValue(executor, "executor", ExecutorService.class).isShutdown();
        this.terminated = ReflectionUtils.getFieldValue(executor, "executor", ExecutorService.class).isTerminated();
        this.defaultTimeout = ReflectionUtils.getSafeFieldValue(executor, "defaultTimeout", Long.class);
        this.timeout = ReflectionUtils.getSafeFieldValue(executor, "timeout", Long.class);
    }

    /**
     * @return
     */
    public Class<? extends RejectedExecutionHandler> getRejectedExecutionHandler() {
        return rejectedExecutionHandler;
    }

    /**
     * @return
     */
    public boolean isWaitForTasksToCompleteOnShutdown() {
        return waitForTasksToCompleteOnShutdown;
    }

    /**
     * @return
     */
    public long getAwaitTerminationMillis() {
        return awaitTerminationMillis;
    }

    /**
     * @return
     */
    public boolean isShutdown() {
        return shutdown;
    }

    /**
     * @return
     */
    public boolean isTerminated() {
        return terminated;
    }

    /**
     * @return
     */
    public Long getDefaultTimeout() {
        return defaultTimeout;
    }

    /**
     * @return
     */
    public Long getTimeout() {
        return timeout;
    }

}
