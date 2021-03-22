package com.example.demospringdi;

// 리얼 서브젝트
public class DefaultBookService implements BookService {

    @Override
    public void rent(Book book) {
        System.out.println("rent: " + book.getTitle());
    }

    @Override
    public void returnBook(Book book) {
        System.out.println("returnBook: " + book.getTitle());
    }
}
