package com.yaudong.assignment.bookstoreservice.controller;

import com.yaudong.assignment.bookstoreservice.dto.book.BookListFilterCriteria;
import com.yaudong.assignment.bookstoreservice.model.Book;
import com.yaudong.assignment.bookstoreservice.dto.book.BookQuantityView;
import com.yaudong.assignment.bookstoreservice.service.BookService;
import com.yaudong.assignment.bookstoreservice.utils.reportgenerator.CsvReportGenerator;
import com.yaudong.assignment.bookstoreservice.utils.reportgenerator.XmlReportGenerator;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RequestMapping("/books")
@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private CsvReportGenerator csvGenerator;

    @Autowired
    private XmlReportGenerator xmlGenerator;

    @GetMapping
    public List<Book> getBooks(BookListFilterCriteria filterCriteria) {
        System.out.println(filterCriteria);
        return bookService.getBooksByCriteria(filterCriteria);
    }

    // TODO: Verify the price is a valid price
    // Verify if all fields are present
    @PostMapping
    public void addBooks(@RequestBody List<Book> books) {
        bookService.addBooks(books);
    }

    @DeleteMapping
    public void deleteBooks(@RequestParam(value = "id", defaultValue = "") List<Long> ids) {
        bookService.deleteBooks(ids);
    }

    @GetMapping("/quantity")
    public Map<Long, BookQuantityView> getBooksQuantity(@RequestParam(value = "id", defaultValue = "") List<Long> ids) {
        return bookService.retrieveQuantity(ids);
    }

    // TODO: Reject -ve quantity
    @PostMapping("/quantity")
    public void updateBooksQuantity(@RequestBody Map<Long, Integer> bookQuantities) {
        bookService.updateQuantity(bookQuantities);
    }

    @GetMapping("/report/csv")
    public void exportInventoryCsvReport(HttpServletResponse response) {
        try {
            response.setContentType("text/csv");
            response.addHeader("Content-Disposition", "attachment; filename=\"inventory_report.csv\"");
            csvGenerator.generateInventoryReport(bookService.getBooks(), response.getWriter());
        } catch (IOException e) {
            // TODO: Send response with correct error code and do logging
            e.printStackTrace();
        }
    }

    @GetMapping("/report/xml")
    public void exportInventoryXmlReport(HttpServletResponse response) {
        try {
            response.setContentType("text/xml");
            response.addHeader("Content-Disposition", "attachment; filename=\"inventory_report.xml\"");
            xmlGenerator.generateInventoryReport(bookService.getBooks(), response.getWriter());
        } catch (IOException e) {
            // TODO: Send response with correct error code and do logging
            e.printStackTrace();
        }
    }
}
