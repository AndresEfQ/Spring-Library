package com.andresefq.Library.controllers;

import com.andresefq.Library.exceptions.MyException;
import com.andresefq.Library.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/register")
    public String register() {
        return "author_form.html";
    }

    @PostMapping("/register")
    public String register(@RequestParam String name) {

        try {
            authorService.createAuthor(name);
        } catch (MyException e) {
            Logger.getLogger(AuthorController.class.getName()).log(Level.SEVERE, null, e);
            return "author_form.html";
        }

        return "index.html";
    }
}
