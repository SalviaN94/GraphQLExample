package com.nick.example.GraphQLExample.service;

import com.nick.example.GraphQLExample.Entity.Author;
import com.nick.example.GraphQLExample.Entity.Book;
import com.nick.example.GraphQLExample.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public Book createBook(Book book){
        Book existingBook = bookRepository.findByName(book.getName());
        if(existingBook == null) {
            return this.bookRepository.save(book);
        }
        return existingBook;
    }

    @Transactional(readOnly = true)
    public List<Book> getAllBooks(){
        return this.bookRepository.findAll().stream().collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<Book> getBook(long id){
        return this.bookRepository.findById(id);
    }

}
