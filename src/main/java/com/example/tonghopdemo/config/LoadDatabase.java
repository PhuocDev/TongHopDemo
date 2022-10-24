//package com.example.tonghopdemo.config;
//
//import com.example.tonghopdemo.entities.Book;
//import com.example.tonghopdemo.repository.BookRepository;
//import com.example.tonghopdemo.service.BookService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration()
//public class LoadDatabase {
//    private final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);
//
////    @Autowired
////    BookService bookService;
//    @Bean
//    CommandLineRunner initDatabase(BookService bookService) {
//        return args -> {
//            Book sampleData = new Book("book default", "author default",
//                    "description default", 100000);
//            bookService.insertBook(sampleData);
//            logger.info("Preloading: 1 - 10");
//        };
//    }
//}
