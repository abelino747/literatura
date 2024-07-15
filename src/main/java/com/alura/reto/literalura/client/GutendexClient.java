package com.alura.reto.literalura.client;

import com.alura.reto.literalura.model.Author;
import com.alura.reto.literalura.model.Book;
import com.alura.reto.literalura.service.AuthorService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class GutendexClient {

    private final RestTemplate restTemplate;
    private final AuthorService authorService;

    @Autowired
    public GutendexClient(RestTemplate restTemplate, AuthorService authorService) {
        this.restTemplate = restTemplate;
        this.authorService = authorService;
    }

    public Book fetchBookByTitle(String title) {
        String url = "https://gutendex.com/books?search=" + title;
        JsonNode response = restTemplate.getForObject(url, JsonNode.class);

        if (response != null && response.has("results")) {
            JsonNode bookData = response.get("results").get(0);
            Book book = new Book();
            book.setTitle(bookData.get("title").asText());
            book.setLanguage(bookData.get("languages").get(0).asText());
            book.setDownloads(bookData.get("download_count").asInt());

            JsonNode authorData = bookData.get("authors").get(0);
            Author author = new Author();
            author.setFirstName(authorData.get("first_name").asText());
            author.setLastName(authorData.get("last_name").asText());

            // Guardar autor y libro en la base de datos
            authorService.saveAuthor(author);
            book.setAuthor(author);

            return book;
        }
        return null;
    }
}
