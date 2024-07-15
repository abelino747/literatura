package com.alura.reto.literalura.service;


import com.alura.reto.literalura.client.GutendexClient;
import com.alura.reto.literalura.model.Book;
import com.alura.reto.literalura.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final GutendexClient gutendexClient;

    public BookService(BookRepository bookRepository, GutendexClient gutendexClient) {
        this.bookRepository = bookRepository;
        this.gutendexClient = gutendexClient;
    }

    public List<Book> findBooksByTitle(String title) {
        List<Book> books = bookRepository.findByTitleContaining(title);

        if (books.isEmpty()) {
            Book book = gutendexClient.fetchBookByTitle(title);
            if (book != null) {
                bookRepository.save(book);
                books.add(book);
            }
        }

        return books;
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> findBooksByLanguage(String language) {
        return bookRepository.findByLanguage(language);
    }

    public void saveBook(Book book) {
        bookRepository.save(book);
    }

}
