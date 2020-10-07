package com.nick.example.GraphQLExample.service;

import com.nick.example.GraphQLExample.entity.Author;
import com.nick.example.GraphQLExample.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Transactional
    public Author createAuthor(Author author){
        Author existingAuthor = authorRepository.findByFirstNameAndLastName(author.getFirstName(),author.getLastName());
        if(existingAuthor == null) {
            return this.authorRepository.save(author);
        }
        return existingAuthor;
    }

    @Transactional(readOnly = true)
    public List<Author> getAllAuthors(int count){
        return this.authorRepository.findAll().stream().limit(count).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<Author> getAuthor(long id){
        return this.authorRepository.findById(id);
    }
}
