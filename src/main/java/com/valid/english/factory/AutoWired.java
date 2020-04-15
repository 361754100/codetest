package com.valid.english.factory;

import java.lang.annotation.*;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoWired {
    String value() default "";
}
