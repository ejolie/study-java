package org.example;

import java.awt.print.Book;

/**
 * Hello world!
 *
 */
public class App {

    static String name;

    static {
        name = "hello";
    }

    public static void main( String[] args ) {
        ClassLoader classLoader = App.class.getClassLoader();
        System.out.println(classLoader); // AppClassLoader(애플리케이션 클래스로더)
        System.out.println(classLoader.getParent()); // PlatformClassLoader(플랫폼 클래스로더)
        System.out.println(classLoader.getParent().getParent()); // null
        // -> BootstrapClassLoader(부트스트랩 클래스로더) 이지만 native 코드로 구현되어 볼 수 없음
    }
}
