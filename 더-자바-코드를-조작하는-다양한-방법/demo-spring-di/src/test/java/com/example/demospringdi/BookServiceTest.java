package com.example.demospringdi;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

class BookServiceTest {

//    BookService bookService = new BookServiceProxy(new DefaultBookService());

    // 자바 다이나믹 프록시의 가장 큰 제약사항
    // - 클래스의 프록시 생성 불가, 반드시 인터페이스 있어야 함!
    // - DefaultBookService 로는 생성 불가
    BookService bookService = (BookService) Proxy.newProxyInstance(BookService.class.getClassLoader(), new Class[]{BookService.class}, new InvocationHandler() {
        // 리얼 서브젝트
        BookService bookService = new DefaultBookService();

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
           if (method.getName().equals("rent")) { // rent 메서드에만 적용
               System.out.println("aaaa");
               Object invoke = method.invoke(bookService, args);
               System.out.println("bbbb");
               return invoke;
           }
           return method.invoke(bookService, args);
        }
    });

    @Test
    public void di() {
        Book book = new Book();
        book.setTitle("spring");
        bookService.rent(book);
        bookService.returnBook(book);
    }
}