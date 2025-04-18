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

import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * 
 * @since 
 * @author Florian Lestic
 */
public class ScheduledThreadPoolExecutorDTO extends ThreadPoolExecutorDTO {

    private final boolean removeOnCancelPolicy;
    private final boolean continueExistingPeriodicTasksAfterShutdownPolicy;
    private final boolean executeExistingDelayedTasksAfterShutdownPolicy;

    /**
     * @param executor
     */
    public ScheduledThreadPoolExecutorDTO(final ScheduledThreadPoolExecutor executor) {
        super(executor);
        this.removeOnCancelPolicy = executor.getRemoveOnCancelPolicy();
        this.continueExistingPeriodicTasksAfterShutdownPolicy = executor.getContinueExistingPeriodicTasksAfterShutdownPolicy();
        this.executeExistingDelayedTasksAfterShutdownPolicy = executor.getExecuteExistingDelayedTasksAfterShutdownPolicy();
    }

    /**
     * @return
     */
    public boolean isRemoveOnCancelPolicy() {
        return removeOnCancelPolicy;
    }

    /**
     * @return
     */
    public boolean isContinueExistingPeriodicTasksAfterShutdownPolicy() {
        return continueExistingPeriodicTasksAfterShutdownPolicy;
    }

    /**
     * @return
     */
    public boolean isExecuteExistingDelayedTasksAfterShutdownPolicy() {
        return executeExistingDelayedTasksAfterShutdownPolicy;
    }

}
