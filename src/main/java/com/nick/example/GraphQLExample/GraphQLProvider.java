package com.nick.example.GraphQLExample;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import com.nick.example.GraphQLExample.GraphQLDataFetchers.GraphQLAuthorDataFetchers;
import com.nick.example.GraphQLExample.GraphQLDataFetchers.GraphQLBookDataFetchers;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

@Component
public class GraphQLProvider {

    private GraphQL graphQL;

    @Autowired
    GraphQLAuthorDataFetchers graphQLAuthorDataFetchers;

    @Autowired
    GraphQLBookDataFetchers graphQLBookDataFetchers;

    @Bean
    public GraphQL graphQL(){
        return graphQL;
    }

    @PostConstruct
    public void init() throws IOException{
        URL url = Resources.getResource("graphql/author.graphqls");
        String sdl = Resources.toString(url, Charsets.UTF_8);
        GraphQLSchema graphQLSchema = buildSchema(sdl);
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    private GraphQLSchema buildSchema(String sdl)throws IOException{
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);

       /* Agregar otro schema */
        URL url = Resources.getResource("graphql/book.graphqls");
        String aux = Resources.toString(url, Charsets.UTF_8);
        typeRegistry.merge(new SchemaParser().parse(aux));
        /*                                                          */

        /* Agregar otro schema */
        URL url2 = Resources.getResource("graphql/schema.graphqls");
        String aux2 = Resources.toString(url2, Charsets.UTF_8);
        typeRegistry.merge(new SchemaParser().parse(aux2));
        /*                                                          */

        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("getAuthor", graphQLAuthorDataFetchers.getAuthorDataFetcher()))
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("getBookById", graphQLBookDataFetchers.getBookDataFetcher()))
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("books", graphQLBookDataFetchers.getAllBooksDataFetcher()))
                .type("Mutation", typeWiring -> typeWiring
                        .dataFetcher("createAuthor", graphQLAuthorDataFetchers.createAuthorDataFetcher()))
                .type("Mutation", typeWiring -> typeWiring
                        .dataFetcher("createBook", graphQLBookDataFetchers.createBookDataFetcher()))
                .type("Mutation", typeWiring -> typeWiring
                        .dataFetcher("createBookFull", graphQLBookDataFetchers.createBookFullFetcher()))
                .type("Mutation", typeWiring -> typeWiring
                        .dataFetcher("updateBook", graphQLBookDataFetchers.updateBookDataFetcher()))
                .type("Mutation", typeWiring -> typeWiring
                        .dataFetcher("deleteBook", graphQLBookDataFetchers.deleteBookDataFetcher()))
                .build();
    }
}
