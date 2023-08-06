package com.yaudong.assignment.bookstoreservice.controller;

import com.yaudong.assignment.bookstoreservice.dto.book.BookListFilterCriteria;
import com.yaudong.assignment.bookstoreservice.model.Book;
import com.yaudong.assignment.bookstoreservice.dto.book.BookQuantityView;
import com.yaudong.assignment.bookstoreservice.service.BookService;
import com.yaudong.assignment.bookstoreservice.utils.reportgenerator.CsvReportGenerator;
import com.yaudong.assignment.bookstoreservice.utils.reportgenerator.XmlReportGenerator;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RequestMapping("/books")
@RestController
@Slf4j
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private CsvReportGenerator csvGenerator;

    @Autowired
    private XmlReportGenerator xmlGenerator;

    @GetMapping
    public List<Book> getBooks(BookListFilterCriteria filterCriteria) {
        return bookService.getBooksByCriteria(filterCriteria);
    }

    @PostMapping
    public ResponseEntity<String> addBooks(@RequestBody @Valid List<Book> books) {
        try {
            bookService.addBooks(books);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurs when creating new books", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public void deleteBooks(@RequestParam(value = "id") List<Long> ids) {
        bookService.deleteBooks(ids);
    }

    @GetMapping("/quantity")
    public Map<Long, BookQuantityView> getBooksQuantity(@RequestParam(value = "id") List<Long> ids) {
        return bookService.retrieveQuantity(ids);
    }

    @PostMapping("/quantity")
    public ResponseEntity<String> updateBooksQuantity(@RequestBody Map<Long, Integer> bookQuantities) {
        boolean isValidRequest = bookQuantities.values().stream().allMatch(quantity -> quantity > 0);
        if (isValidRequest) {
            bookService.updateQuantity(bookQuantities);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/report/csv")
    public ResponseEntity<String> exportInventoryCsvReport(HttpServletResponse response) {
        try {
            response.setContentType("text/csv");
            response.addHeader("Content-Disposition", "attachment; filename=\"inventory_report.csv\"");
            csvGenerator.generateInventoryReport(bookService.getAllBooks(), response.getWriter());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            log.error("Error occurs while generating book inventory CSV report", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/report/xml")
    public ResponseEntity<String> exportInventoryXmlReport(HttpServletResponse response) {
        try {
            response.setContentType("text/xml");
            response.addHeader("Content-Disposition", "attachment; filename=\"inventory_report.xml\"");
            xmlGenerator.generateInventoryReport(bookService.getAllBooks(), response.getWriter());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            log.error("Error occurs while generating book inventory XML report", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> handleMissingParams(MissingServletRequestParameterException ex) {
        String name = ex.getParameterName();
        System.out.println(name + " parameter is missing");
        return new ResponseEntity<>(name + " parameter is missing", HttpStatus.BAD_REQUEST);
    }
}
