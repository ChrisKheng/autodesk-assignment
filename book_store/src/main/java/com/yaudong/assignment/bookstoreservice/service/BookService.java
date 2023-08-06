package com.yaudong.assignment.bookstoreservice.service;

import com.yaudong.assignment.bookstoreservice.dto.book.BookListFilterCriteria;
import com.yaudong.assignment.bookstoreservice.model.Book;
import com.yaudong.assignment.bookstoreservice.dto.book.BookQuantityView;
import com.yaudong.assignment.bookstoreservice.model.specification.BookSpecs;
import com.yaudong.assignment.bookstoreservice.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getBooksByCriteria(BookListFilterCriteria criteria) {
        Specification spec = Specification
                .where(BookSpecs.titleEquals(criteria.getTitle()))
                .and(BookSpecs.authorEquals(criteria.getAuthor()))
                .and(BookSpecs.isbnEquals(criteria.getIsbn()))
                .and(BookSpecs.isQuantityEqual(criteria.getQuantity()))
                .and(BookSpecs.isAvailable(criteria.getIsAvailable()))
                .and(BookSpecs.isGreaterThanOrEqualToMinPrice(criteria.getMinPrice()))
                .and(BookSpecs.isLesserThanOrEqualToMaxPrice(criteria.getMaxPrice()));

        return bookRepository.findAll(spec);
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
