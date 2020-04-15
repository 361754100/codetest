package com.valid.english.service;

import com.valid.english.factory.Component;

@Component
public class PersonService {

    public String test() {
        System.out.println("PersonService... test");
        return "person a";
    }

}
