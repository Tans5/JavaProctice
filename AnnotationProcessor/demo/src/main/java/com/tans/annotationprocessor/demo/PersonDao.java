package com.tans.annotationprocessor.demo;


import com.tans.annotationprocessor.annotations.Query;
import com.tans.annotationprocessor.annotations.SqlDao;

@SqlDao
public class PersonDao {

    @Query("select * from person where name=:name")
    public Person queryPerson(String name) {
        return new Person("Tans", "Tan", 24, "");
    }
}
