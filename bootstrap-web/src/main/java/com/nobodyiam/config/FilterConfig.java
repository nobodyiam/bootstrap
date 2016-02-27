package com.nobodyiam.config;

import com.nobodyiam.web.filter.AuthFilter;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;

import javax.servlet.DispatcherType;
import java.util.Arrays;
import java.util.List;

/**
 * Since filter is outside of Spring MVC context, so we need to define a separate configuration class
 * to initialize the custom filters
 */
@Configuration
public class FilterConfig {
    private static final List<String> DEFAULT_URL_MAPPINGS = Arrays.asList("/*");

    @Bean
    public FilterRegistrationBean characterEncodingFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new CharacterEncodingFilter("UTF-8", true));
        registrationBean.setUrlPatterns(DEFAULT_URL_MAPPINGS);

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean httpPutFormContentFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new HttpPutFormContentFilter());
        registrationBean.setUrlPatterns(DEFAULT_URL_MAPPINGS);

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean urlRewriteFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new UrlRewriteFilter());
        registrationBean.setUrlPatterns(DEFAULT_URL_MAPPINGS);

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean authFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new AuthFilter());
        registrationBean.addInitParameter("prefix", "app");
        registrationBean.setUrlPatterns(Arrays.asList("/app/*"));
        registrationBean.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.FORWARD);

        return registrationBean;
    }

}
