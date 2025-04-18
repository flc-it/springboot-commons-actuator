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

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.flcit.commons.core.util.ClassUtils;

/**
 * 
 * @since 
 * @author Florian Lestic
 */
public class ThreadPoolExecutorDTO {

    private final Class<? extends ThreadPoolExecutor> type;
    private final int activeCount;
    private final int corePoolSize;
    private final int largestPoolSize;
    private final int maximumPoolSize;
    private final int poolSize;
    private final boolean allowsCoreThreadTimeOut;
    private final boolean shutdown;
    private final boolean terminated;
    private final boolean terminating;
    private final long taskCount;
    private final long completedTaskCount;
    private final long keepAliveTimeSeconds;
    private final Class<? extends RejectedExecutionHandler> rejectedExecutionHandler;
    private final BlockingQueueDTO queue;

    ThreadPoolExecutorDTO(ThreadPoolExecutor executor) {
        this.type = executor.getClass();
        this.activeCount = executor.getActiveCount();
        this.corePoolSize = executor.getCorePoolSize();
        this.largestPoolSize = executor.getLargestPoolSize();
        this.maximumPoolSize = executor.getMaximumPoolSize();
        this.poolSize = executor.getPoolSize();
        this.allowsCoreThreadTimeOut = executor.allowsCoreThreadTimeOut();
        this.shutdown = executor.isShutdown();
        this.terminated = executor.isTerminated();
        this.terminating = executor.isTerminating();
        this.taskCount = executor.getTaskCount();
        this.completedTaskCount = executor.getCompletedTaskCount();
        this.keepAliveTimeSeconds = executor.getKeepAliveTime(TimeUnit.SECONDS);
        this.rejectedExecutionHandler = ClassUtils.getSafe(executor.getRejectedExecutionHandler());
        this.queue = new BlockingQueueDTO(executor.getQueue());
    }

    /**
     * @return
     */
    public Class<? extends ThreadPoolExecutor> getType() {
        return type;
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
    public int getCorePoolSize() {
        return corePoolSize;
    }

    /**
     * @return
     */
    public int getLargestPoolSize() {
        return largestPoolSize;
    }

    /**
     * @return
     */
    public int getMaximumPoolSize() {
        return maximumPoolSize;
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
    public boolean isAllowsCoreThreadTimeOut() {
        return allowsCoreThreadTimeOut;
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
    public boolean isTerminating() {
        return terminating;
    }

    /**
     * @return
     */
    public long getTaskCount() {
        return taskCount;
    }

    /**
     * @return
     */
    public long getCompletedTaskCount() {
        return completedTaskCount;
    }

    /**
     * @return
     */
    public long getKeepAliveTimeSeconds() {
        return keepAliveTimeSeconds;
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
    public BlockingQueueDTO getQueue() {
        return queue;
    }

}
