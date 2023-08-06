package com.yaudong.assignment.bookstoreservice.controller;

import com.yaudong.assignment.bookstoreservice.model.Book;
import com.yaudong.assignment.bookstoreservice.model.BookQuantityView;
import com.yaudong.assignment.bookstoreservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    // TODO: Verify the price is a valid price
    @PostMapping("/books")
    public void addBooks(@RequestBody List<Book> books) {
        bookService.addBooks(books);
    }

    @DeleteMapping("/books")
    public void deleteBooks(@RequestParam(value = "id", defaultValue = "") List<Long> ids) {
        bookService.deleteBooks(ids);
    }

    @GetMapping("/books/quantity")
    public Map<Long, BookQuantityView> getBooksQuantity(@RequestParam(value = "id", defaultValue = "") List<Long> ids) {
        return bookService.retrieveQuantity(ids);
    }

    @PostMapping("/books/quantity")
    public void updateBooksQuantity(@RequestBody Map<Long, Integer> bookQuantities) {
        bookService.updateQuantity(bookQuantities);
    }
}
