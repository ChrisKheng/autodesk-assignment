package com.yaudong.assignment.bookstoreservice.utils.reportgenerator;

import com.yaudong.assignment.bookstoreservice.model.Book;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Component
public class CsvReportGenerator extends InventoryReportGenerator {
    @Override
    protected void genReport(List<Book> books, Writer writer) throws IOException {
        CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
        for (Book book: books) {
            printer.printRecord(
                    book.getBookId(),
                    book.getTitle(),
                    book.getPublisher(),
                    book.getAuthor(),
                    book.getIsbn(),
                    book.getPrice(),
                    book.getQuantity()
            );
        }

    }
}
