package com.example.tonghopdemo.bookApi.controller;

import com.example.tonghopdemo.bookApi.entities.Book;
import com.example.tonghopdemo.exception.APImessages;
import com.example.tonghopdemo.bookApi.repository.BookRepository;
import com.example.tonghopdemo.bookApi.service.BookService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "Book APIs")
public class WebController {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookService bookService;

    public WebController(BookService bookService) {
        this.bookService = bookService;
    }
    @PostConstruct
    public void createDB() {
        for (int i = 1 ; i < 5 ; i++) {
            Book sampleData = new Book("book" + i, "author" + i, "description is " + i, i*22.500);
            bookService.insertBook(sampleData);
        }
    }
    @GetMapping(value = {"/", "/book/all"})
    public ResponseEntity<List<Book>> index() {
        List<Book> bookList = bookService.getBooks();
        return new ResponseEntity<List<Book>>(bookList, HttpStatus.OK);
    }

    @GetMapping("book/{bookId}")
    public ResponseEntity<?> getBook(@PathVariable(name = "bookId") Long bookId){
        if(bookService.getBookById(bookId) == null) {
            APImessages error = new APImessages("Not found book id: "+ bookId);
            return new ResponseEntity<APImessages>(error, HttpStatus.NOT_FOUND);
        } else {
            Book book = bookService.getBookById(bookId);
            return new ResponseEntity<Book>(book, HttpStatus.OK);
        }
    }

    @DeleteMapping("delete/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable(name = "bookId") Long bookId) {

        if (bookService.getBookById(bookId) == null) {
            APImessages error = new APImessages("Not found book id: "+ bookId);
            return new ResponseEntity<APImessages>(error, HttpStatus.NOT_FOUND);
        } else {
            bookService.deleteBookById(bookId);
            APImessages message = new APImessages("Deleted book id: "+ bookId);
            return new ResponseEntity<APImessages>(message, HttpStatus.OK);
        }
    }

    @PostMapping("book/add")
    public ResponseEntity<?> addBook(@RequestBody @Valid Book book) {
        if (bookService.insertBook(book) != null) {
            return new ResponseEntity<Book>(book, HttpStatus.OK);
        } else {
            APImessages error = new APImessages("Can not insert book: "+ book.getId());
            return new ResponseEntity<APImessages>(error, HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("book/{id}")
    public ResponseEntity<?> updateBook(@PathVariable("id") Long bookId, @RequestBody Book newBook) {
        Book book = bookService.getBookById(bookId);
        if (book == null) {
            APImessages error = new APImessages("Not found book id: "+ bookId);
            return new ResponseEntity<APImessages>(error, HttpStatus.NOT_FOUND);
        } else {
            bookService.updateBook(bookId, newBook);
            return new ResponseEntity<Book>(newBook, HttpStatus.OK);
        }
    }
}
