package com.tans.annotationprocessor.demo;

import com.tans.annotationprocessor.annotations.Query;
import com.tans.annotationprocessor.annotations.SqlDao;

@SqlDao
public interface Person2Dao {

    @Query("select * from person where name=:name")
    String queryPerson(String name);
}
