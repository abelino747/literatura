package com.alura.reto.literalura.controller;



import com.alura.reto.literalura.client.GutendexClient;
import com.alura.reto.literalura.model.Book;
import com.alura.reto.literalura.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/books")

public class BookController {

    private final BookService bookService;
    private final GutendexClient gutendexClient;

    public BookController(BookService bookService, GutendexClient gutendexClient) {
        this.bookService = bookService;
        this.gutendexClient = gutendexClient;
    }

    @GetMapping("/search")
    public List<Book> searchBooksByTitle(@RequestParam String title) {
        List<Book> books = bookService.findBooksByTitle(title);
        if (books.isEmpty()) {
            Book book = gutendexClient.fetchBookByTitle(title);
            if (book != null) {
                bookService.saveBook(book);
                books.add(book);
            }
        }
        return books;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAllBooks();
    }

    @GetMapping("/language")
    public List<Book> getBooksByLanguage(@RequestParam String language) {
        return bookService.findBooksByLanguage(language);
    }

    @PostMapping
    public void addBook(@RequestBody Book book) {
        bookService.saveBook(book);
    }

}
