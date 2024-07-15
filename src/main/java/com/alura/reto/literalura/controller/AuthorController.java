package com.alura.reto.literalura.controller;

import com.alura.reto.literalura.model.Author;
import com.alura.reto.literalura.service.AuthorService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/authors")

public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.findAllAuthors();
    }

    @PostMapping
    public void addAuthor(@RequestBody Author author) {
        authorService.saveAuthor(author);
    }
}
