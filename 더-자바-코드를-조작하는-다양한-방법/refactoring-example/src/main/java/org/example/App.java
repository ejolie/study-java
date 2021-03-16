package org.example;

import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args )  {
        Arrays.stream(Book.class.getAnnotations()).forEach(System.out::println);

        // 부모 annotation 포함 O
        Arrays.stream(MyBook.class.getAnnotations()).forEach(System.out::println);

        // 부모 annotation 포함 X
        Arrays.stream(MyBook.class.getDeclaredFields()).forEach(System.out::println);

        Arrays.stream(Book.class.getDeclaredFields()).forEach(f -> {
            Arrays.stream(f.getAnnotations()).forEach(a -> {
                if (a instanceof MyAnnotation) {
                    MyAnnotation myAnnotation = (MyAnnotation) a;
                    System.out.println(myAnnotation.name());
                    System.out.println(myAnnotation.value());
                    System.out.println(myAnnotation.number());
                }
            });
        });
    }

    private void useReflectionApi() throws ClassNotFoundException {
        // - 클래스 객체를 가져오는 세 가지 방법
        // 1. 타입.class
        Class<Book> bookClass1 = Book.class;

        // 2. 인스턴스.getClass()
        Book book = new Book();
        Class<? extends Book> bookClass2 = book.getClass();

        // 3. Class.forName(FQCN)
        Class<?> bookClass3 = Class.forName("org.example.Book");

        // - getFields(): public 필드만 가져옴
        // Arrays.stream(bookClass1.getFields()).forEach(System.out::println);

        // - getDeclaredFields(): 모든 필드 가져옴
        Arrays.stream(bookClass1.getDeclaredFields()).forEach(System.out::println);

        // - 필드와 값 가져오기
        Arrays.stream(bookClass1.getDeclaredFields()).forEach(f -> {
            try {
                // 접근 불가능한 필드에 접근 가능하게 만들어줌
                f.setAccessible(true);
                System.out.printf("%s %s\n", f, f.get(book));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        // - 상위 클래스 가져오기
        Class<? super MyBook> superclass = MyBook.class.getSuperclass();
        System.out.println(superclass);

        // - 인터페이스 가져오기
        Class<?>[] interfaces = MyBook.class.getInterfaces();
        Arrays.stream(interfaces).forEach(System.out::println);

        // - modifier 가져오기
        Arrays.stream(Book.class.getDeclaredFields()).forEach(f -> {
            int modifiers = f.getModifiers();
            System.out.println(f);
            System.out.println(Modifier.isPrivate(modifiers));
            System.out.println(Modifier.isStatic(modifiers));
        });

        // - 메소드 가져오기
        Arrays.stream(Book.class.getMethods()).forEach(m -> {
            int modifiers = m.getModifiers();
            System.out.println(m);
            System.out.println(modifiers);
        });
    }
}
