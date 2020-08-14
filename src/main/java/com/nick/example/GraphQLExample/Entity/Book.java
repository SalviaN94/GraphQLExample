package com.nick.example.GraphQLExample.Entity;

import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name="page_count", nullable = false)
    private int pageCount;

    @ManyToOne
    private Author author;

    protected Book(){

    }

    public Book(Long id, String name, int pageCount) {
        this.id = id;
        this.name = name;
        this.pageCount = pageCount;
    }

    public Book(String name, int pageCount){
        this.name = name;
        this.pageCount = pageCount;
    }

    public Book(String name, int pageCount, Author author) {
        this.name = name;
        this.pageCount = pageCount;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }


}
