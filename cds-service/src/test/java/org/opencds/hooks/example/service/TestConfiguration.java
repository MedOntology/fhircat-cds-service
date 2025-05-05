package org.opencds.hooks.example.service;

import org.opencds.webapp.config.cdshooks.r4.R4CdsHooksConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Import(R4CdsHooksConfig.class)
@PropertySource(value = "classpath:/config/opencds-hooks.properties")
public class TestConfiguration {
}
