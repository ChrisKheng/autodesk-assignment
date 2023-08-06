package com.yaudong.assignment.bookstoreservice.repository;

import com.yaudong.assignment.bookstoreservice.model.Book;
import com.yaudong.assignment.bookstoreservice.dto.book.BookQuantityView;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    List<Book> findAll(Specification<Book> spec);

    List<BookQuantityView> getBookQuantityByBookIdIn(Iterable<Long> ids);
}
