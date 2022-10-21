package com.example.tonghopdemo;

import com.example.tonghopdemo.entities.Book;
import com.example.tonghopdemo.repository.BookRepository;
import com.example.tonghopdemo.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
class TongHopDemoApplicationTests {

    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @Test
    public void getBooksTest() {
        when(bookRepository.findAll()).thenReturn(
                Stream.of(new Book("1", "2", "3", 4),
                        new Book("11", "22", "33", 44))
                        .collect(Collectors.toList())
        );
        assertEquals(2, bookService.getBooks().size());
    }
    @Test
    public void saveBookTest() {
        Book book = new Book("Phuoc", "Author", "Description", 1200);
        when(bookRepository.save(book)).thenReturn(book);
        assertEquals(book, bookService.insertBook2(book));
    }
    @Test
    public void deleteBookTest() {
        Book book = new Book("Phuoc", "Author", "Description", 1200);
        bookService.deleteBookById(book.getId());
        verify(bookRepository, times(1)).deleteById(book.getId());
    }

//    @Test
//    public void getBookByIdTest() {
//        int id = 9;
//        when(bookRepository.findById((long) id)).thenReturn(
//                Stream.of(new Book("phuoc", "1", "1", 44)).collect(Collectors.toList())
//        );
//        assertEquals(1, bookService.getBookById(id).size());
//    }
}
