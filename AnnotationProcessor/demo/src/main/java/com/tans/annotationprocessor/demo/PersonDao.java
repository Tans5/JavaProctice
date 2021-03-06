package com.tans.annotationprocessor.demo;


import com.tans.annotationprocessor.annotations.Query;
import com.tans.annotationprocessor.annotations.SqlDao;

@SqlDao
public interface PersonDao {

    @Query("select * from person where name=:name")
    String queryPerson(String name);
}
