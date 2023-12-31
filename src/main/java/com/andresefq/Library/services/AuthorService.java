package com.andresefq.Library.services;

import com.andresefq.Library.entities.Author;
import com.andresefq.Library.exceptions.MyException;
import com.andresefq.Library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Transactional
    public void createAuthor(String name) throws MyException {

        validate(name);
        Author author = new Author();
        author.setName(name);
        authorRepository.save(author);
    }

    public List<Author> listAuthors() {
        return authorRepository.findAll();
    }

    @Transactional
    public void modifyAuthor(String id, String name) throws MyException {

        validate(name);
        Optional<Author> response = authorRepository.findById(id);

        if (response.isPresent()) {
            Author author = response.get();
            author.setName(name);
            authorRepository.save(author);
        }
    }

    private void validate (String name) throws MyException {
        if (name == null || name.isEmpty()) {
            throw new MyException("Name can't be null or empty");
        }
    }
}
