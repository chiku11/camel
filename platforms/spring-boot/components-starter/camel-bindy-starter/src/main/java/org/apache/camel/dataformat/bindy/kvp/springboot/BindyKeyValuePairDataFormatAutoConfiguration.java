/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.dataformat.bindy.kvp.springboot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import org.apache.camel.CamelContext;
import org.apache.camel.CamelContextAware;
import org.apache.camel.RuntimeCamelException;
import org.apache.camel.dataformat.bindy.kvp.BindyKeyValuePairDataFormat;
import org.apache.camel.spi.DataFormat;
import org.apache.camel.spi.DataFormatCustomizer;
import org.apache.camel.spi.DataFormatFactory;
import org.apache.camel.spring.boot.CamelAutoConfiguration;
import org.apache.camel.spring.boot.DataFormatConfigurationProperties;
import org.apache.camel.spring.boot.util.GroupCondition;
import org.apache.camel.util.IntrospectionSupport;
import org.apache.camel.util.ObjectHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.AllNestedConditions;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * Generated by camel-package-maven-plugin - do not edit this file!
 */
@Generated("org.apache.camel.maven.packaging.SpringBootAutoConfigurationMojo")
@Configuration
@Conditional(BindyKeyValuePairDataFormatAutoConfiguration.Condition.class)
@AutoConfigureAfter(name = "org.apache.camel.spring.boot.CamelAutoConfiguration")
@EnableConfigurationProperties({DataFormatConfigurationProperties.class,
        BindyKeyValuePairDataFormatConfiguration.class})
public class BindyKeyValuePairDataFormatAutoConfiguration
        extends
            AllNestedConditions {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(BindyKeyValuePairDataFormatAutoConfiguration.class);
    @Autowired
    private CamelContext camelContext;
    @Autowired(required = false)
    private List<DataFormatCustomizer<BindyKeyValuePairDataFormat>> customizers;
    @Autowired
    private DataFormatConfigurationProperties globalConfiguration;
    @Autowired
    private BindyKeyValuePairDataFormatConfiguration dataformatConfiguration;

    public BindyKeyValuePairDataFormatAutoConfiguration() {
        super(ConfigurationPhase.REGISTER_BEAN);
    }

    @ConditionalOnBean(CamelContext.class)
    public static class OnCamelContext {
    }

    @ConditionalOnBean(CamelAutoConfiguration.class)
    public static class OnCamelAutoConfiguration {
    }

    @ConditionalOnBean(CamelAutoConfiguration.class)
    public static class Condition extends GroupCondition {
        public Condition() {
            super("camel.dataformat", "camel.dataformat.bindy-kvp");
        }
    }

    @Bean(name = "bindy-kvp-dataformat-factory")
    @ConditionalOnClass(CamelContext.class)
    @ConditionalOnMissingBean(BindyKeyValuePairDataFormat.class)
    public DataFormatFactory configureBindyKeyValuePairDataFormatFactory()
            throws Exception {
        return new DataFormatFactory() {
            public DataFormat newInstance() {
                BindyKeyValuePairDataFormat dataformat = new BindyKeyValuePairDataFormat();
                if (CamelContextAware.class
                        .isAssignableFrom(BindyKeyValuePairDataFormat.class)) {
                    CamelContextAware contextAware = CamelContextAware.class
                            .cast(dataformat);
                    if (contextAware != null) {
                        contextAware.setCamelContext(camelContext);
                    }
                }
                try {
                    Map<String, Object> parameters = new HashMap<>();
                    IntrospectionSupport.getProperties(dataformatConfiguration,
                            parameters, null, false);
                    IntrospectionSupport.setProperties(camelContext,
                            camelContext.getTypeConverter(), dataformat,
                            parameters);
                } catch (Exception e) {
                    throw new RuntimeCamelException(e);
                }
                boolean useCustomizers = globalConfiguration.getCustomizer()
                        .isEnabled()
                        && dataformatConfiguration.getCustomizer().isEnabled();
                if (useCustomizers && ObjectHelper.isNotEmpty(customizers)) {
                    for (DataFormatCustomizer<BindyKeyValuePairDataFormat> customizer : customizers) {
                        LOGGER.debug(
                                "Configure dataformat {}, with customizer {}",
                                dataformat, customizer);
                        customizer.customize(dataformat);
                    }
                }
                return dataformat;
            }
        };
    }
}