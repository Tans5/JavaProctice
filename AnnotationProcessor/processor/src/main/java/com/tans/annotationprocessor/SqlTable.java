package com.tans.annotationprocessor;

import java.lang.annotation.*;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
@Inherited
public @interface SqlTable {
    String name();
    String primaryKey() default "";
}
