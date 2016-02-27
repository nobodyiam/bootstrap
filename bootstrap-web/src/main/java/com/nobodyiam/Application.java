package com.nobodyiam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
 */
@SpringBootApplication(exclude = DispatcherServletAutoConfiguration.class)
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
