package com.yaudong.assignment.bookstoreservice.dto.book;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookListFilterCriteria {
    private String title;
    private String author;
    private String isbn;
    private Boolean isAvailable;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Integer quantity;
}
