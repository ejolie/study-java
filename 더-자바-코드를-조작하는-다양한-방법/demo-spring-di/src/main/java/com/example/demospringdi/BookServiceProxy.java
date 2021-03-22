package com.example.demospringdi;

// 프록시
public class BookServiceProxy implements BookService {

    // 리얼 서브젝트
    BookService bookService;

    public BookServiceProxy(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void rent(Book book) {
        System.out.println("aaaa");
        bookService.rent(book);
        System.out.println("bbbb");
    }

    @Override
    public void returnBook(Book book) {
        System.out.println("aaaa");
        bookService.returnBook(book);
    }
}
