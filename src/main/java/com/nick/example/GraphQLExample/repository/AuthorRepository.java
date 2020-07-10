package com.nick.example.GraphQLExample.repository;

import com.nick.example.GraphQLExample.Entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findByFirstNameAndLastName(String firstName, String lastName);
}
