package com.nick.example.GraphQLExample.service;

import com.nick.example.GraphQLExample.entity.Author;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class AuthorServiceTest {

    @Autowired
    AuthorService authorService;

    @Test
    @DirtiesContext
    void createNonExistingAuthor() {
        Author author = authorService.createAuthor(new Author("Nick", "Salvia"));
        assertNotNull(author);
    }

    @Test
    void createExistingAuthor() {
        Author author = authorService.createAuthor(new Author("John", "Doe"));
        assertNotNull(author);
    }

    @Test
    void getAllAuthors() {
        List<Author> authors = new ArrayList<Author>();
        authors.add(new Author("John", "Doe"));

        List<Author> storedAuthors = authorService.getAllAuthors(2);

        assertEquals(authors, storedAuthors);
    }

    @Test
    void getAuthor() {
        Author author = new Author("John", "Doe");

        assertEquals(author, authorService.getAuthor(20001).get());
    }
}