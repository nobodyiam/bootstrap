package com.nobodyiam.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by Jason on 7/5/15.
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.nobodyiam")
public class AppConfig {
}
