package com.nobodyiam.aop;

import com.nobodyiam.dto.Greeting;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Jason on 7/9/15.
 */
@Aspect
@Component
public class GreetingAspect {
    private static final Logger logger = LoggerFactory.getLogger(GreetingAspect.class);

    @Pointcut("execution(* com.nobodyiam.api.GreetingService.insertGreeting(..))")
    public void insertGreeting() {}

    @Around("insertGreeting()")
    public int logCreatedGreetingId(ProceedingJoinPoint joinPoint) throws Throwable {
        int insertedRows = (Integer)joinPoint.proceed();
        if (logger.isDebugEnabled()) {
            Object[] args = joinPoint.getArgs();
            String greetingContent = "";
            long greetingId = 0;
            if (args != null && args.length > 0 && args[0] instanceof Greeting) {
                greetingContent = ((Greeting) args[0]).getContent();
                greetingId = ((Greeting) args[0]).getId();
            }
            logger.debug(String.format("Creating greeting with content: %s, new greeting id is: %d", greetingContent, greetingId));
        }

        return insertedRows;
    }


}
