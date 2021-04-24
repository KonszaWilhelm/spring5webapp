package com.wilhelm.konsza.controllers;

import com.wilhelm.konsza.repositories.AuthorRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthorsController {

    private final AuthorRepo authorRepo;

    public AuthorsController(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @RequestMapping("/authors")
    public String getAuthors(Model model) {

        model.addAttribute("authors", authorRepo.findAll());

        return "authors/authors_list";
    }
}
