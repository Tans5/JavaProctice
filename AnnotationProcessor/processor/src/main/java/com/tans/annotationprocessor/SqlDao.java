package com.tans.annotationprocessor;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface SqlDao {
}
