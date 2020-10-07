package com.nick.example.GraphQLExample.GraphQLDataFetchers;

import com.nick.example.GraphQLExample.entity.Author;
import com.nick.example.GraphQLExample.service.AuthorService;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

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
            LinkedHashMap<String, Object> author = dataFetchingEnvironment.getArgument("input");
            Author createAuthor = authorService.createAuthor(new Author((String)author.get("firstName"),
                    (String)author.get("lastName")));
            LinkedHashMap<String, Object> returnedMap = new LinkedHashMap<>();
            returnedMap.put("author", createAuthor);
            return returnedMap;

        };
    }

}
