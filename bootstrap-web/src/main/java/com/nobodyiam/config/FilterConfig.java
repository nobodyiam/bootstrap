package com.nobodyiam.config;

import com.nobodyiam.web.filter.AuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Since filter is outside of Spring MVC context, so we need to define a separate configuration class
 * to initialize the custom filters
 */
@Configuration
public class FilterConfig {
    @Bean
    public AuthFilter authFilter() {
        return new AuthFilter();
    }
}
