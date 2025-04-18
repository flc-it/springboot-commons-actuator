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
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.concurrent.ExecutorConfigurationSupport;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import org.flcit.springboot.commons.actuator.executor.BaseExecutor;
import org.flcit.springboot.commons.actuator.executor.update.ConfigurationExecutorUpdate;
import org.flcit.springboot.commons.actuator.executor.update.ExecutorAction;
import org.flcit.springboot.commons.actuator.executor.update.ThreadPoolExecutorUpdate;
import org.flcit.springboot.commons.actuator.util.CommonsActuatorUtils;
import org.flcit.springboot.commons.core.util.BeanUtils;
import org.flcit.commons.core.util.ReflectionUtils;

/**
 * 
 * @since 
 * @author Florian Lestic
 */
@Endpoint(id = "executors")
public class ExecutorsEndpoint extends AbstractBeansEndpoint<Executor, BaseExecutor> {

    /**
     * @param context
     */
    public ExecutorsEndpoint(ConfigurableApplicationContext context) {
        super(context);
    }

    /**
     *
     */
    @Override
    @ReadOperation
    public Map<String, BaseExecutor> list() {
        return super.list();
    }

    /**
     *
     */
    @Override
    @ReadOperation
    public BaseExecutor get(@Selector String name) {
        return super.get(name);
    }

    @Override
    BaseExecutor convert(String name, Executor value) {
        return CommonsActuatorUtils.convert(name, value);
    }

    /**
     * @param action
     */
    @WriteOperation
    public void action(@Selector ExecutorAction action) {
        for (Entry<String, ExecutorConfigurationSupport> entry: listBeans(ExecutorConfigurationSupport.class).entrySet()) {
            action(entry.getValue(), action);
        }
    }

    /**
     * @param name
     * @param action
     */
    @WriteOperation
    public void action(@Selector String name, @Selector ExecutorAction action) {
        action(getBean(name, ExecutorConfigurationSupport.class), action);
    }

    private void action(final ExecutorConfigurationSupport executorConfiguration, final ExecutorAction action) {
        final ThreadPoolExecutor tpe = getThreadPoolExecutor(executorConfiguration);
        if (tpe == null) {
            return;
        }
        if (action == ExecutorAction.purge) {
            purge(tpe);
        } else if (action == ExecutorAction.clear) {
            clear(tpe);
        } else if (action == ExecutorAction.start) {
            start(tpe, executorConfiguration);
        } else if (action == ExecutorAction.shutdown) {
            shutdown(tpe);
        } else if (action == ExecutorAction.shutdownNow) {
            shutdownNow(tpe);
        }
    }

    private static final ThreadPoolExecutor getThreadPoolExecutor(final Executor executor) {
        if (executor instanceof ExecutorConfigurationSupport) {
            return getThreadPoolExecutor((ExecutorConfigurationSupport) executor);
        }
        return null;
    }

    private static final ThreadPoolExecutor getThreadPoolExecutor(final ExecutorConfigurationSupport executorConfiguration) {
        if (executorConfiguration instanceof ThreadPoolTaskScheduler) {
            return ((ThreadPoolTaskScheduler) executorConfiguration).getScheduledThreadPoolExecutor();
        } else if (executorConfiguration instanceof ThreadPoolTaskExecutor) {
            return ((ThreadPoolTaskExecutor) executorConfiguration).getThreadPoolExecutor();
        }
        return null;
    }

    private static final void purge(final ThreadPoolExecutor executor) {
        executor.purge();
    }

    private static final void clear(final ThreadPoolExecutor executor) {
        executor.getQueue().clear();
    }

    private static final void start(final ThreadPoolExecutor executor, final ExecutorConfigurationSupport executorConfiguration) {
        if (canStart(executor)) {
            executorConfiguration.initialize();
        }
    }

    private static final void shutdown(final ThreadPoolExecutor executor) {
        if (canShutdown(executor)) {
            executor.shutdown();
        }
    }

    private static final void shutdownNow(final ThreadPoolExecutor executor) {
        if (canShutdown(executor)) {
            for (Runnable task : executor.shutdownNow()) {
                if (task instanceof Future) {
                    ((Future<?>) task).cancel(true);
                }
            }
        }
    }

    private static final boolean canShutdown(final ThreadPoolExecutor executor) {
        return !executor.isShutdown()
                && !executor.isTerminated()
                && !executor.isTerminating();
    }

    private static final boolean canStart(final ThreadPoolExecutor executor) {
        return !canShutdown(executor);
    }

    /**
     * @param name
     * @param threadPriority
     * @param threadNamePrefix
     * @param daemon
     * @param waitForTasksToCompleteOnShutdown
     * @param awaitTerminationMillis
     * @param defaultTimeout
     * @param timeout
     * @param beanName
     * @param concurrencyLimit
     * @param throttleActive
     * @param poolSize
     * @param corePoolSize
     * @param keepAliveSeconds
     * @param maxPoolSize
     * @param queueCapacity
     * @param allowsCoreThreadTimeOut
     * @param removeOnCancelPolicy
     * @param continueExistingPeriodicTasksAfterShutdownPolicy
     * @param executeExistingDelayedTasksAfterShutdownPolicy
     */
    @SuppressWarnings("java:S107")
    @WriteOperation
    public void update(@Selector String name,
            @Nullable Integer threadPriority,
            @Nullable String threadNamePrefix,
            @Nullable Boolean daemon,
            @Nullable Boolean waitForTasksToCompleteOnShutdown,
            @Nullable Long awaitTerminationMillis,
            @Nullable Long defaultTimeout,
            @Nullable Long timeout,
            @Nullable String beanName,
            @Nullable Integer concurrencyLimit,
            @Nullable Boolean throttleActive,
            @Nullable Integer poolSize,
            @Nullable Integer corePoolSize,
            @Nullable Integer keepAliveSeconds,
            @Nullable Integer maxPoolSize,
            @Nullable Integer queueCapacity,
            @Nullable Boolean allowsCoreThreadTimeOut,
            @Nullable Boolean removeOnCancelPolicy,
            @Nullable Boolean continueExistingPeriodicTasksAfterShutdownPolicy,
            @Nullable Boolean executeExistingDelayedTasksAfterShutdownPolicy) {
        final Executor executor = getBean(name);
        BeanUtils.copyNonNullProperties(
                new ConfigurationExecutorUpdate()
                .setAwaitTerminationMillis(awaitTerminationMillis)
                .setBeanName(beanName)
                .setConcurrencyLimit(concurrencyLimit)
                .setDaemon(daemon)
                .setDefaultTimeout(defaultTimeout)
                .setPoolSize(maxPoolSize)
                .setThreadNamePrefix(threadNamePrefix)
                .setThreadPriority(threadPriority)
                .setThrottleActive(throttleActive)
                .setTimeout(timeout)
                .setWaitForTasksToCompleteOnShutdown(waitForTasksToCompleteOnShutdown),
                executor);
        final ThreadPoolExecutorUpdate executorUpdate = new ThreadPoolExecutorUpdate()
                .setAllowsCoreThreadTimeOut(allowsCoreThreadTimeOut)
                .setContinueExistingPeriodicTasksAfterShutdownPolicy(continueExistingPeriodicTasksAfterShutdownPolicy)
                .setCorePoolSize(corePoolSize)
                .setExecuteExistingDelayedTasksAfterShutdownPolicy(executeExistingDelayedTasksAfterShutdownPolicy)
                .setKeepAliveSeconds(keepAliveSeconds)
                .setMaxPoolSize(maxPoolSize)
                .setQueueCapacity(queueCapacity)
                .setRemoveOnCancelPolicy(removeOnCancelPolicy);
        BeanUtils.copyNonNullProperties(
                executorUpdate,
                executor);
        final ThreadPoolExecutor tpe = getThreadPoolExecutor(executor);
        BeanUtils.copyNonNullProperties(
                executorUpdate,
                tpe);
        if (tpe != null) {
            if (keepAliveSeconds != null) {
                tpe.setKeepAliveTime(keepAliveSeconds, TimeUnit.SECONDS);
            }
            if (queueCapacity != null
                    && tpe.getQueue() instanceof LinkedBlockingQueue<?>) {
                ReflectionUtils.setFieldValue(tpe.getQueue(), tpe.getQueue().getClass(), "capacity", int.class, queueCapacity);
            }
        }
    }

}
