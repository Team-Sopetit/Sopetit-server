package com.soptie.server.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.soptie.server.batch.BatchProperties;

@Configuration
@EnableConfigurationProperties({BatchProperties.class})
public class PropertyConfig {
}
