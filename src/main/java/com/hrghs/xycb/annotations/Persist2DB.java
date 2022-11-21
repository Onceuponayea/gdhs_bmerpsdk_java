package com.hrghs.xycb.annotations;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Persist2DB {
    /**
     * automatically save object to db
     * @return
     */
    String dataSourceName() default "";
}
