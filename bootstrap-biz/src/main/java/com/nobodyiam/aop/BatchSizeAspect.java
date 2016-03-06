package com.nobodyiam.aop;

import com.nobodyiam.annotation.RestrictBatchSize;
import com.nobodyiam.util.ConstantsUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by Jason on 7/11/15.
 */
@Aspect
@Component
@PropertySource("classpath:bootstrap-biz.properties")
public class BatchSizeAspect {
    private int maxBatchSize;

    @Pointcut("@annotation(com.nobodyiam.annotation.RestrictBatchSize)")
    public void methodWithBatchSizeCheck() {
    }

    @Before("methodWithBatchSizeCheck()")
    public void checkBatchSize(JoinPoint joinPoint) throws NoSuchMethodException {
        Signature signature = joinPoint.getSignature();
        if (!(signature instanceof MethodSignature)) {
            return;
        }
        // must use target since the annotation is annotated on the impl, not api
        Method method = joinPoint.getTarget().getClass().getMethod(signature.getName(),
                ((MethodSignature) signature).getParameterTypes());
        if (method == null || !method.isAnnotationPresent(RestrictBatchSize.class)) {
            return;
        }
        RestrictBatchSize restrictBatchSize = method.getAnnotation(RestrictBatchSize.class);
        int paramIndex = restrictBatchSize.paramIndex();
        if (paramIndex < 0) {
            return;
        }
        int batchSize = (Integer) (joinPoint.getArgs())[paramIndex];
        checkArgument(batchSize <= maxBatchSize, String.format("Batch size(%d) exceeds max allowed size: %d", batchSize, ConstantsUtil.MaxBatchSize()));
    }

    @Value("${batch-size.max}")
    public void setMaxBatchSize(int maxBatchSize) {
        this.maxBatchSize = maxBatchSize;
    }
}
