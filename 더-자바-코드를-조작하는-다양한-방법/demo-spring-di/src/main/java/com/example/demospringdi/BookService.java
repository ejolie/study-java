package com.example.demospringdi;

// 서브젝트
public interface BookService {

    void rent(Book book);

    void returnBook(Book book);
}
