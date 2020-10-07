package com.nick.example.GraphQLExample.repository;

import com.nick.example.GraphQLExample.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findByFirstNameAndLastName(String firstName, String lastName);
    Author findAuthorById(Long id);
}
