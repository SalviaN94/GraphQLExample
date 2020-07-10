package com.nick.example.GraphQLExample.service;

import com.nick.example.GraphQLExample.Entity.Author;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class AuthorServiceTest {

    @Autowired
    AuthorService authorService;

    @Test
    void createAuthorNonExisting() {
        Author author = authorService.createAuthor("Daniel", "Salvia");
        assertNotNull(author);
    }

    @Test
    void createAuthorExisting() {
        Author author = authorService.createAuthor("Nick", "Salvia");
        assertNotNull(author);
    }

    @Test
    void getAllAuthors() {

    }

    @Test
    void getAuthor() {
    }
}