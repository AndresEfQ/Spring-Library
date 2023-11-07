package com.andresefq.Library.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Book {
    @Id
    private Long isbn;
    private String title;
    private Integer instances;
    @Temporal(TemporalType.DATE)
    private Date active;
    @ManyToOne
    private Author author;
    @ManyToOne
    private Publisher publisher;

    public Book() {
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getInstances() {
        return instances;
    }

    public void setInstances(Integer instances) {
        this.instances = instances;
    }

    public Date getActive() {
        return active;
    }

    public void setActive(Date active) {
        this.active = active;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
