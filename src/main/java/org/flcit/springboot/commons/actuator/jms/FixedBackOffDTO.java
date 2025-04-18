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

package org.flcit.springboot.commons.actuator.jms;

import org.springframework.util.backoff.FixedBackOff;

/**
 * 
 * @since 
 * @author Florian Lestic
 */
public class FixedBackOffDTO {

    private Long interval;
    private Long maxAttempts;

    /**
     * @param backOff
     */
    public FixedBackOffDTO(FixedBackOff backOff) {
        this(backOff.getInterval(), backOff.getMaxAttempts());
    }

    /**
     * @param interval
     * @param maxAttempts
     */
    public FixedBackOffDTO(Long interval, Long maxAttempts) {
        this.interval = interval;
        this.maxAttempts = maxAttempts;
    }

    /**
     * @return
     */
    public Long getInterval() {
        return interval;
    }
    /**
     * @param interval
     */
    public void setInterval(Long interval) {
        this.interval = interval;
    }
    /**
     * @return
     */
    public Long getMaxAttempts() {
        return maxAttempts;
    }
    /**
     * @param maxAttempts
     */
    public void setMaxAttempts(Long maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

}
