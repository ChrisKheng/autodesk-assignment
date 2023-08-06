package com.yaudong.assignment.bookstoreservice.repository;

import com.yaudong.assignment.bookstoreservice.model.Book;
import com.yaudong.assignment.bookstoreservice.model.BookQuantityView;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<BookQuantityView> getBookQuantityByBookIdIn(Iterable<Long> ids);
}
