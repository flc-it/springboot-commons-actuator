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

import org.springframework.core.task.SimpleAsyncTaskExecutor;

/**
 * 
 * @since 
 * @author Florian Lestic
 */
public class SimpleAsyncTaskExecutorDTO extends BaseExecutor {

    private final int concurrencyLimit;
    private final boolean throttleActive;

    /**
     * @param name
     * @param executor
     */
    public SimpleAsyncTaskExecutorDTO(final String name, final SimpleAsyncTaskExecutor executor) {
        super(name, executor);
        this.concurrencyLimit = executor.getConcurrencyLimit();
        this.throttleActive = executor.isThrottleActive();
    }

    /**
     * @return
     */
    public int getConcurrencyLimit() {
        return concurrencyLimit;
    }

    /**
     * @return
     */
    public boolean isThrottleActive() {
        return throttleActive;
    }

}
