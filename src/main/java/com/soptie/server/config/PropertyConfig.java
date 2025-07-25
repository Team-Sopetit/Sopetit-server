package com.soptie.server.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.soptie.server.batch.BatchProperties;
import com.soptie.server.config.properties.LoggingProperties;

@Configuration
@EnableConfigurationProperties({BatchProperties.class, LoggingProperties.class})
public class PropertyConfig {
}
