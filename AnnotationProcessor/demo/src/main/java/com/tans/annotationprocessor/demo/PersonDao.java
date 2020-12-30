package com.tans.annotationprocessor.demo;


import com.example.processors.Query;
import com.example.processors.SqlDao;

@SqlDao
public class PersonDao {

    @Query("select * from person where name=:name")
    public Person queryPerson(String name) {
        return new Person("Tans", "Tan", 24, "");
    }
}
