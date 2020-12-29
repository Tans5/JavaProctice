package com.tans.annotationprocessor.demo;


import com.tans.annotationprocessor.SqlField;
import com.tans.annotationprocessor.SqlTable;

@SqlTable(name = "person", primaryKey = "first_name")
public class Person {
    @SqlField(name = "first_name", type = SqlField.SqlFieldType.STRING)
    private final String firstName;
    @SqlField(name = "second_name", type = SqlField.SqlFieldType.STRING)
    private final String secondName;
    @SqlField(name = "age", type = SqlField.SqlFieldType.INTEGER)
    private final int age;
    @SqlField(name = "first_name", type = SqlField.SqlFieldType.STRING)
    private final String address;

    public Person(String firstName, String secondName, int age, String address) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return firstName + secondName;
    }

}
