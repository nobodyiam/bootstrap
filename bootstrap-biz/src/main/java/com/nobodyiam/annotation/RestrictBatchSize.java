package com.nobodyiam.annotation;

import java.lang.annotation.*;

/**
 * Created by Jason on 7/11/15.
 */

/**
 * For batch methods like query a list, we must restrict the batch size to protect the overall service responsiveness
 * We could annotate those methods with RestrictBatchSize, specifying the 0 based parameter index so that we could
 * check the batch size at runtime.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RestrictBatchSize {
    /**
     * please make sure the parameter index is 0 based, e.g. the first parameter's index is 0.
     * @return 0 based parameter index
     */
    int paramIndex();
}
