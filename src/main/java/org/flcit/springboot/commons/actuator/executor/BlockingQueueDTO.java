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

import java.util.concurrent.BlockingQueue;

import org.flcit.commons.core.util.ClassUtils;
import org.flcit.commons.core.util.ReflectionUtils;

/**
 * 
 * @since 
 * @author Florian Lestic
 */
public class BlockingQueueDTO {

    private final int size;
    private final Integer capacity;
    private final int remainingCapacity;
    private final Class<? extends BlockingQueue<Runnable>> type;

    BlockingQueueDTO(BlockingQueue<Runnable> queue) {
        this.size = queue.size();
        this.capacity = ReflectionUtils.getSafeFieldValue(queue, "capacity", int.class);
        this.type = ClassUtils.getSafe(queue);
        this.remainingCapacity = queue.remainingCapacity();
    }

    /**
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * @return
     */
    public Integer getCapacity() {
        return capacity;
    }

    /**
     * @return
     */
    public int getRemainingCapacity() {
        return remainingCapacity;
    }

    /**
     * @return
     */
    public Class<? extends BlockingQueue<Runnable>> getType() {
        return type;
    }

}
