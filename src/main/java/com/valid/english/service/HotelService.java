package com.valid.english.service;

import com.valid.english.factory.AutoWired;
import com.valid.english.factory.Component;

@Component
public class HotelService {

    @AutoWired
    private PersonService personService;

    public PersonService getPersonService() {
        return personService;
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    public void order() {
        String personMsg = personService.test();
        System.out.println("order hotel service...");
    }

}
