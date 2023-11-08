package com.andresefq.Library.services;

import com.andresefq.Library.entities.Publisher;
import com.andresefq.Library.exceptions.MyException;
import com.andresefq.Library.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {

    @Autowired
    PublisherRepository publisherRepository;

    @Transactional
    public void createPublisher(String name) throws MyException {

        validate(name);
        Publisher publisher = new Publisher();
        publisher.setName(name);
        publisherRepository.save(publisher);
    }

    public List<Publisher> listPublishers() {
        return publisherRepository.findAll();
    }

    @Transactional
    public void modifyPublisher(String id, String name) throws MyException {

        validate(name);
        Optional<Publisher> response = publisherRepository.findById(id);

        if (response.isPresent()) {
            Publisher publisher = response.get();
            publisher.setName(name);
            publisherRepository.save(publisher);
        }
    }

    private void validate (String name) throws MyException {
        if (name == null || name.isEmpty()) {
            throw new MyException("Name can't be null or empty");
        }
    }
}
