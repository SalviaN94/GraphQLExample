package com.nick.example.GraphQLExample.service;

import com.nick.example.GraphQLExample.entity.Author;
import com.nick.example.GraphQLExample.entity.Book;
import com.nick.example.GraphQLExample.repository.AuthorRepository;
import com.nick.example.GraphQLExample.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private EntityManager em;

    @Transactional
    public Book createBook(Book book, Long authorId){
        Book existingBook = bookRepository.findByName(book.getName());
        if(existingBook != null) {
            return existingBook;
        }
        Author author = authorRepository.findAuthorById(authorId);
        if(author == null)
            return null;
        author.addBooks(book);
        book.setAuthor(author);
        em.persist(book);
        em.flush();

        return book;
    }

    @Transactional
    public Book updateBook(Book book){
        this.bookRepository.save(book);
        return book;
    }

    @Transactional
    public Book deleteBook(long id){
        Book book = this.bookRepository.findById(id).get();
        this.bookRepository.deleteById(id);
        return book;
    }

    @Transactional(readOnly = true)
    public List<Book> getAllBooks(){
        return new ArrayList<>(this.bookRepository.findAll());
    }

    @Transactional(readOnly = true)
    public Optional<Book> getBook(long id){
        return this.bookRepository.findById(id);
    }

}
