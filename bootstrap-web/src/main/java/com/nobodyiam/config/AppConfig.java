package com.nobodyiam.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;

/**
 * Created by Jason on 7/5/15.
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(value = "com.nobodyiam",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = FilterConfig.class))
public class AppConfig {
}
