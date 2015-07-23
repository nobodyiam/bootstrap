package com.nobodyiam.config;

import org.springframework.context.annotation.*;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Jason on 7/5/15.
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(value = "com.nobodyiam",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = FilterConfig.class))
public class AppConfig {
    private final int MAX_THREADS = 30; // max concurrent running job
    private final int BLOCKING_QUEUE_SIZE = 50; // max waiting jobs

    @Bean
    public ExecutorService executorService() throws Exception {
        return threadPoolExecutorFactoryBean().getObject();
    }

    @Bean
    public ThreadPoolExecutorFactoryBean threadPoolExecutorFactoryBean() {
        ThreadPoolExecutorFactoryBean threadPoolExecutorFactoryBean = new ThreadPoolExecutorFactoryBean();
        threadPoolExecutorFactoryBean.setMaxPoolSize(MAX_THREADS);
        threadPoolExecutorFactoryBean.setCorePoolSize(MAX_THREADS);
        threadPoolExecutorFactoryBean.setQueueCapacity(BLOCKING_QUEUE_SIZE);
        threadPoolExecutorFactoryBean.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());

        return threadPoolExecutorFactoryBean;
    }
}
