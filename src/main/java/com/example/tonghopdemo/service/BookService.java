package com.example.tonghopdemo.service;

import com.example.tonghopdemo.entities.Book;

import java.util.List;

public interface BookService {
    List<Book> getBooks();

    Book getBookById(Long id);
    void deleteBookById(Long id);
    void updateBook(Long id, Book newBook);

    Book insertBook(Book book);

    List<Book> searchBookByName(String name);

}
