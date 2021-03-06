package com.nick.example.GraphQLExample.repository;

import com.nick.example.GraphQLExample.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByName(String name);
}
