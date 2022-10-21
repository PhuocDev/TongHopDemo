package com.example.tonghopdemo.repository;

import com.example.tonghopdemo.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Transactional  //nhớ thêm 2 annotiation này vàoo để thực hiện custom query
    @Modifying
    @Query("update Book u set u.name = ?1, u.description = ?2, " +
            "u.author = ?3, u.price = ?4 " +
            " where u.id = ?5")
    void update(String title, String des, String author, double price, Long id);
}
