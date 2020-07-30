package com.nick.example.GraphQLExample.GraphQLDataFetchers;

import com.nick.example.GraphQLExample.Entity.Book;
import com.nick.example.GraphQLExample.repository.BookRepository;
import com.nick.example.GraphQLExample.service.AuthorService;
import com.nick.example.GraphQLExample.service.BookService;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public DataFetcher createBookDataFetcher() {
        return dataFetchingEnvironment -> {
            String name = dataFetchingEnvironment.getArgument("name");
            int pageCount = dataFetchingEnvironment.getArgument("pageCount");

            return bookService.createBook(new Book(name, pageCount));
        };
    }

    public DataFetcher createBookFullFetcher(){
        return dataFetchingEnvironment -> {
            Book book = dataFetchingEnvironment.getArgument("book");
            System.out.println(book);

            return bookService.createBook(book);
        };
    }
}
