package com.tans.annotationprocessor.demo;

import com.tans.processors.gen.Person2DaoImpl;
import com.tans.processors.gen.PersonDaoImpl;

public class Main {

    public static void main(String[] args) {
        PersonDao personDao = new PersonDaoImpl();
        Person2Dao person2Dao = new Person2DaoImpl();
        personDao.queryPerson("Tans");
        person2Dao.queryPerson("Tans");
    }
}
