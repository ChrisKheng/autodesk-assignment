package com.yaudong.assignment.bookstoreservice.utils.reportgenerator;

import com.yaudong.assignment.bookstoreservice.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Writer;
import java.util.List;

public abstract class InventoryReportGenerator {
    private static final Logger logger = LoggerFactory.getLogger(InventoryReportGenerator.class);

    public void generateInventoryReport(List<Book> books, Writer writer) {
        doLogging(books);
        genReport(books, writer);
    }

    protected void doLogging(List<Book> books) {
        logger.info(String.format("Generating report for %s books", books.size()));
    }

    abstract protected void genReport(List<Book> books, Writer writer);
}
