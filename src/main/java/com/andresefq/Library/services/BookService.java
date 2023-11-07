package com.andresefq.Library.services;

import com.andresefq.Library.entities.Book;
import com.andresefq.Library.entities.Author;
import com.andresefq.Library.entities.Publisher;
import com.andresefq.Library.repository.AuthorRepository;
import com.andresefq.Library.repository.BookRepository;
import com.andresefq.Library.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private PublisherRepository publisherRepository;

    @Transactional
    public void createBook(Long isbn, String title, Integer instances, String authorId, String publisherId) {
        Book book = new Book();
        Author author = authorRepository.findById(authorId).get();
        Publisher publisher = publisherRepository.findById(publisherId).get();

        book.setIsbn(isbn);
        book.setTitle(title);
        book.setInstances(instances);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setActive(new Date());

        bookRepository.save(book);
    }

    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    @Transactional
    public void modifyBook(Long isbn, String title, String authorId, String publisherId, Integer instances) {

        Optional<Book> bookResponse = bookRepository.findById(isbn);
        Optional<Author> authorResponse = authorRepository.findById(authorId);
        Optional<Publisher> publisherResponse = publisherRepository.findById(publisherId);

        Author author = new Author();
        Publisher publisher = new Publisher();

        if (authorResponse.isPresent()) {
            author = authorResponse.get();
        }

        if (publisherResponse.isPresent()) {
            publisher = publisherResponse.get();
        }

        if (bookResponse.isPresent()) {
            Book book = bookResponse.get();
            book.setTitle(title);
            book.setAuthor(author);
            book.setPublisher(publisher);
            book.setInstances(instances);

            bookRepository.save(book);
        }
    }
}
