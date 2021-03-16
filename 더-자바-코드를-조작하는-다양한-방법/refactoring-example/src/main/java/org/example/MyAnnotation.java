package org.example;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
@Inherited
public @interface MyAnnotation {

    String name() default "eunjeong";

    // value: 이름 명시 필요 X
    // ex) @MyAnnotation("hello")
    String value();

    int number() default 100;
}
