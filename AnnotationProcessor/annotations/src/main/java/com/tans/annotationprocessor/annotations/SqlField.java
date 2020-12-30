package com.tans.annotationprocessor.annotations;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface SqlField {
    String name();
    SqlFieldType type();
    boolean isPrimaryKey() default false;

    enum SqlFieldType { STRING, INTEGER, FLOAT }
}
