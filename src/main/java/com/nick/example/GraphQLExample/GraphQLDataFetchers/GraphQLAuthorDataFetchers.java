package com.nick.example.GraphQLExample.GraphQLDataFetchers;

import com.nick.example.GraphQLExample.Entity.Author;
import com.nick.example.GraphQLExample.service.AuthorService;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GraphQLAuthorDataFetchers {

    @Autowired
    AuthorService authorService;

    public DataFetcher getAuthorDataFetcher() {
        return dataFetchingEnvironment -> {
            String authorId = dataFetchingEnvironment.getArgument("id");
            return authorService.getAuthor(Long.parseLong(authorId));
        };
    }

    public DataFetcher getAllAuthorsDataFetcher() {
        return dataFetchingEnvironment -> {
            int count = Integer.parseInt(dataFetchingEnvironment.getArgument("count"));
            return authorService.getAllAuthors(count);
        };
    }

    public DataFetcher createAuthorDataFetcher() {
        return dataFetchingEnvironment -> {
            String firstName = dataFetchingEnvironment.getArgument("firstName");
            String lastName = dataFetchingEnvironment.getArgument("lastName");

            return authorService.createAuthor(firstName, lastName);
        };
    }

}
