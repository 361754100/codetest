package com.valid.english;

import com.valid.english.factory.BeanFactory;
import com.valid.english.service.PersonService;

/**
 * author: Jianzhang Mo
 * date: 2020-03-09
 * desc: code test
 */

public class Main {

    public static void main(String args[]) {
        BeanFactory.getInstance().init("com.valid.english");
        PersonService personService = (PersonService) BeanFactory.getInstance().getBean(PersonService.class);
        personService.test();
    }

}
