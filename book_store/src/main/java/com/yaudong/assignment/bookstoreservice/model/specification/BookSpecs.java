package com.yaudong.assignment.bookstoreservice.model.specification;

import com.yaudong.assignment.bookstoreservice.model.Book;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class BookSpecs {
    public static Specification<Book> titleEquals(String title) {
        return (root, query, criteriaBuilder) ->
                title == null ?
                        criteriaBuilder.conjunction() :
                        criteriaBuilder.equal(root.get("title"), title);
    }

    public static Specification<Book> authorEquals(String author) {
        return (root, query, criteriaBuilder) ->
                author == null ?
                        criteriaBuilder.conjunction() :
                        criteriaBuilder.equal(root.get("author"), author);
    }

    public static Specification<Book> isbnEquals(String isbn) {
        return (root, query, criteriaBuilder) ->
                isbn == null ?
                        criteriaBuilder.conjunction() :
                        criteriaBuilder.equal(root.get("isbn"), isbn);
    }

    public static Specification<Book> isAvailable(Boolean isAvailable) {
        return (root, query, criteriaBuilder) -> {
            if (isAvailable == null) {
                return criteriaBuilder.conjunction();
            } else if (isAvailable == true) {
                return criteriaBuilder.greaterThan(root.get("quantity"), 0);
            } else {
                return criteriaBuilder.equal(root.get("quantity"), 0);
            }
        };
    }

    public static Specification<Book> isGreaterThanOrEqualToMinPrice(BigDecimal minPrice) {
        return (root, query, criteriaBuilder) ->
                minPrice == null ?
                        criteriaBuilder.conjunction() :
                        criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    public static Specification<Book> isLesserThanOrEqualToMaxPrice(BigDecimal maxPrice) {
        return (root, query, criteriaBuilder) ->
                maxPrice == null ?
                        criteriaBuilder.conjunction() :
                        criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

    public static Specification<Book> isQuantityEqual(Integer quantity) {
        return (root, query, criteriaBuilder) ->
                quantity == null ?
                        criteriaBuilder.conjunction() :
                        criteriaBuilder.lessThanOrEqualTo(root.get("quantity"), quantity);
    }
}
