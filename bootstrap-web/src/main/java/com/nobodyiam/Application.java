package com.nobodyiam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.Arrays;

/**
 * Created by Jason on 2/27/16.
 * The following classes are set not to be auto configured:
 * 1. DispatcherServletAutoConfiguration - since I want to configure the servlet's url mapping
 * 2. DataSourceAutoConfiguration - since it only allows one data source, so I have to disable it
 * 3. DataSourceTransactionManagerAutoConfiguration - since it only allows one data source, so I have to disable it
 */
@SpringBootApplication(exclude = {DispatcherServletAutoConfiguration.class, DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class})
public class Application {
    @Bean
    public ServletRegistrationBean dispatcherServlet() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean();
        DispatcherServlet dispatcherServlet = new DispatcherServlet();

        dispatcherServlet.setContextClass(AnnotationConfigWebApplicationContext.class);
        dispatcherServlet.setContextConfigLocation(null);
        registrationBean.setServlet(dispatcherServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.setUrlMappings(Arrays.asList("/app/*"));
        registrationBean.setName("Spring MVC Dispatcher Servlet");

        return registrationBean;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
