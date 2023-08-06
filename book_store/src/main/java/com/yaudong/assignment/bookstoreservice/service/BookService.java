package com.yaudong.assignment.bookstoreservice.service;

import com.yaudong.assignment.bookstoreservice.model.Book;
import com.yaudong.assignment.bookstoreservice.model.BookQuantityView;
import com.yaudong.assignment.bookstoreservice.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getBooks() {
        List<Book> result = new ArrayList<>();
        bookRepository.findAll().forEach(result::add);
        return result;
    }

    @Transactional
    public void addBooks(List<Book> books) {
        bookRepository.saveAll(books);
    }

    @Transactional
    public void deleteBooks(List<Long> ids) {
        bookRepository.deleteAllById(ids);
    }

    public Map<Long, BookQuantityView> retrieveQuantity(List<Long> ids) {
        return bookRepository.getBookQuantityByBookIdIn(ids).stream().collect(Collectors.toMap(b -> b.getBookId(), b -> b));
    }

    @Transactional
    public void updateQuantity(Map<Long, Integer> bookQuantities) {
         Iterable<Book> books = bookRepository.findAllById(bookQuantities.keySet());
         books.forEach(b -> b.setQuantity(bookQuantities.get(b.getBookId())));
         bookRepository.saveAll(books);
    }
}
