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

import org.springframework.util.CustomizableThreadCreator;

/**
 * 
 * @since 
 * @author Florian Lestic
 */
public class BaseExecutor {

    private final String name;
    private final Class<? extends CustomizableThreadCreator> type;
    private final int threadPriority;
    private final String threadNamePrefix;
    private final boolean daemon;

    BaseExecutor(final String name, final CustomizableThreadCreator executor) {
        this.name = name;
        this.type = executor.getClass();
        this.threadPriority = executor.getThreadPriority();
        this.threadNamePrefix = executor.getThreadNamePrefix();
        this.daemon = executor.isDaemon();
    }

    /**
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @return
     */
    public Class<? extends CustomizableThreadCreator> getType() {
        return type;
    }

    /**
     * @return
     */
    public int getThreadPriority() {
        return threadPriority;
    }

    /**
     * @return
     */
    public String getThreadNamePrefix() {
        return threadNamePrefix;
    }

    /**
     * @return
     */
    public boolean isDaemon() {
        return daemon;
    }

}
