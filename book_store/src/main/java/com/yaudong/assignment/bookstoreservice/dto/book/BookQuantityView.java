package com.yaudong.assignment.bookstoreservice.dto.book;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"bookId"})
public interface BookQuantityView {
    long getBookId();

    int getQuantity();
}
