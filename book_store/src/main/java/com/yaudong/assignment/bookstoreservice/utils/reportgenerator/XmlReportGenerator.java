package com.yaudong.assignment.bookstoreservice.utils.reportgenerator;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.yaudong.assignment.bookstoreservice.model.Book;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Component
public class XmlReportGenerator extends InventoryReportGenerator {
    @Override
    protected void genReport(List<Book> books, Writer writer) {
        try {
            XmlMapper mapper = new XmlMapper();
            writer.write(mapper.writer().withRootName("Books").writeValueAsString(books));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
