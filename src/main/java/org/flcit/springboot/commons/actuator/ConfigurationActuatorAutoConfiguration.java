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

package org.flcit.springboot.commons.actuator;

import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnAvailableEndpoint;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import org.flcit.springboot.commons.actuator.endpoint.ConfigurationEndpoint;

/**
 * 
 * @since 
 * @author Florian Lestic
 */
@AutoConfiguration
@ConditionalOnAvailableEndpoint(endpoint = ConfigurationEndpoint.class)
public class ConfigurationActuatorAutoConfiguration {

    /**
     * @param context
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public ConfigurationEndpoint configurationEndpoint(ConfigurableApplicationContext context) {
        return new ConfigurationEndpoint(context);
    }

}
