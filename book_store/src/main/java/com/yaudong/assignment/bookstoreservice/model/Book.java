package com.yaudong.assignment.bookstoreservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@ToString
public class Book {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long bookId;
    private String title;
    private String publisher;
    private String author;
    private String isbn;
    private BigDecimal price;
    private int quantity;

    public Book(String title, String publisher, String author, String isbn, BigDecimal price, int quantity) {
        this.title = title;
        this.publisher = publisher;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
        this.quantity = quantity;
    }
}
