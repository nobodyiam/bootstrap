package com.nobodyiam.aop;

import com.nobodyiam.dto.Greeting;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by Jason on 7/9/15.
 */
@Aspect
@Component
public class GreetingAspect {
    private static final Logger logger = LoggerFactory.getLogger(GreetingAspect.class);

    @Pointcut("execution(* com.nobodyiam.api.GreetingService.insertGreeting(..)) && args(greeting)")
    public void insertGreeting(Greeting greeting) {
    }

    @Pointcut("execution(* com.nobodyiam.api.GreetingService.updateGreeting(..)) && args(greeting)")
    public void updateGreeting(Greeting greeting) {
    }

    @Pointcut("execution(* com.nobodyiam.api.GreetingService.getGreetings(..)) && args(limit, offset)")
    public void getGreetings(int limit, int offset) {
    }

    @Pointcut("execution(* com.nobodyiam.api.GreetingService.getGreeting(..))")
    public void getGreeting() {}

    @Around(value = "insertGreeting(greeting)", argNames = "joinPoint, greeting")
    public int logCreatedGreetingId(ProceedingJoinPoint joinPoint, Greeting greeting) throws Throwable {
        logger.debug(String.format("Creating greeting with content: %s", greeting.getContent()));
        int insertedRows = (Integer) joinPoint.proceed();
        logger.debug(String.format("Greeting created - id: %d, content: %s", greeting.getId(), greeting.getContent()));

        return insertedRows;
    }

    @Before(value = "updateGreeting(greeting)", argNames = "greeting")
    public void beforeUpdatingGreeting(Greeting greeting) {
        logger.debug(String.format("Updating greeting(id=%d) with content: %s", greeting.getId(), greeting.getContent()));
    }

    @AfterThrowing(value = "getGreetings(limit, offset)", argNames = "limit, offset, e", throwing = "e")
    public void logInvalidGreetingsBatch(int limit, int offset, Exception e) {
        logger.error(String.format("Invalid batch when querying greetings - limit: %d, offset: %d, exception: %s", limit, offset, e));
    }

    @After("getGreeting()")
    public void logAfterGetGreeting() {
        logger.debug("Greeting queried...");
    }
}
