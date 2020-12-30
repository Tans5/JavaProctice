package com.tans.annotationprocessor.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
@Inherited
public @interface Query {
    String value();
}
