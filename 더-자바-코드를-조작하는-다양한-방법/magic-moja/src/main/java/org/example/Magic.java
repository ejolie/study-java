package org.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) // TYPE: class, interface, enum
@Retention(RetentionPolicy.SOURCE) // SOURCE: 컴파일 과정에 필요 O, 바이트코드에는 필요 X
public @interface Magic {
}
