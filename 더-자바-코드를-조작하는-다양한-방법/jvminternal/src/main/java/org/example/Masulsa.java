package org.example;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class Masulsa {

    public static void main(String[] args) {
//        System.out.println(new Moja().pullOut());

        new ByteBuddy().redefine(Moja.class)
                .method(named("pullOut"))
                .intercept(FixedValue.value("Rabbit!"))
                .make().saveIn("/Users/eunjeong/Developer/GitHub/study-java/더-자바-코드를-조작하는-다양한-방법/jvminternal/target/classes/org/example/Moja.class");
    }
}
