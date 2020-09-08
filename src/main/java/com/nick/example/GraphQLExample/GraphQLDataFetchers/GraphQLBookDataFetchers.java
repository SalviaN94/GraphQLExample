package com.nick.example.GraphQLExample.GraphQLDataFetchers;

import com.nick.example.GraphQLExample.Entity.Book;
import com.nick.example.GraphQLExample.repository.BookRepository;
import com.nick.example.GraphQLExample.service.AuthorService;
import com.nick.example.GraphQLExample.service.BookService;
import graphql.execution.DataFetcherResult;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class GraphQLBookDataFetchers {

    @Autowired
    BookService bookService;

    public DataFetcher getBookDataFetcher() {
        return dataFetchingEnvironment -> {
            String authorId = dataFetchingEnvironment.getArgument("id");
            return bookService.getBook(Long.parseLong(authorId));
        };
    }

    public DataFetcher getAllBooksDataFetcher() {
        return dataFetchingEnvironment -> {
            //int count = Integer.parseInt(dataFetchingEnvironment.getArgument("count"));
            return bookService.getAllBooks();
        };
    }

    public DataFetcher createBookDataFetcher(){
        return dataFetchingEnvironment -> {
            LinkedHashMap<String, Object> book = dataFetchingEnvironment.getArgument("input");
            Book createBook = bookService.createBook(new Book((String)book.get("name"), (Integer)book.get("pageCount")));
            LinkedHashMap<String, Object> returnedMap = new LinkedHashMap<>();
            returnedMap.put("book", createBook);
            return returnedMap;
        };
    }

    public DataFetcher updateBookDataFetcher(){
        return dataFetchingEnvironment -> {
            LinkedHashMap<String, Object> book = dataFetchingEnvironment.getArgument("input");
            Book updatedBook = bookService.updateBook(new Book(Long.parseLong((String)book.get("id")), (String)book.get("name"),
                    (int)book.get("pageCount")));
            LinkedHashMap<String, Object> returnedMap = new LinkedHashMap<>();
            returnedMap.put("book", updatedBook);
            return returnedMap;
        };
    }

    public DataFetcher deleteBookDataFetcher(){
        return dataFetchingEnvironment -> {
            LinkedHashMap<String, Object> book = dataFetchingEnvironment.getArgument("input");
            long id = Long.parseLong((String)book.get("id"));
            Book deletedBook = bookService.deleteBook(id);
            LinkedHashMap<String, Object> returnedMap = new LinkedHashMap<>();
            returnedMap.put("book", deletedBook);
            return returnedMap;
        };
    }
}
