package com.example.tonghopdemo.service;

import com.example.tonghopdemo.entities.Book;
import com.example.tonghopdemo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImp implements BookService{
    @Autowired
    BookRepository bookRepository;

    public BookServiceImp(BookRepository bookRepository ){
        this.bookRepository = bookRepository;
    }

    public BookServiceImp() {
    }

    @Override
    public List<Book> getBooks() {
        return  bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).get();
    }


    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
        System.out.println("Deleted successfully!");
    }

    @Override
    public void updateBook(Long id, Book newBook) {
        bookRepository.update(newBook.getName(), newBook.getDescription(), newBook.getAuthor(), newBook.getPrice(), id);
    }

    @Override
    public Book insertBook(Book book) {
        bookRepository.save(book);
        return book;
    }
    @Override
    public Book insertBook2(Book book) {
        bookRepository.save(book);
        return book;
    }
    @Override
    public List<Book> searchBookByName(String name) {
        return null;
    }
}
