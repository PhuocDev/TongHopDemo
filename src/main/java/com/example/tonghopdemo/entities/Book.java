package com.example.tonghopdemo.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name="book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @Column(name = "author")
    @NotEmpty(message = "Author cannot be empty")
    private String author;

    @Column(name = "description")
    @NotEmpty(message = "Description cannot be empty")
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "date_created")
    private Date dateCreated;

    public Book() {
        this.dateCreated = new Date();
    }
    public Book(String name, String author, String description, double price) {
        this.name = name;
        this.author = author;
        this.description = description;
        this.price = price;
        this.dateCreated = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
