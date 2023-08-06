package com.yaudong.assignment.bookstoreservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"bookId"})
public interface BookQuantityView {
    long getBookId();

    int getQuantity();
}
