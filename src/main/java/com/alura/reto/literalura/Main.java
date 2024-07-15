package com.alura.reto.literalura;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static BookService bookService; //variable bookService

    public static void main(String[] args) {
        bookService = new BookService(); // instancia de BookService

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Reto Literatura App");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            JPanel panel = new JPanel();
            frame.add(panel);

            JLabel titleLabel = new JLabel("Buscar Libro por Título:");
            JTextField titleField = new JTextField(20);
            JButton searchButton = new JButton("Buscar");
            JTextArea resultArea = new JTextArea(10, 30);

            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String title = titleField.getText();
                    List<Book> books = bookService.findBooksByTitle(title);
                    if (!books.isEmpty()) {
                        StringBuilder sb = new StringBuilder();
                        for (Book book : books) {
                            sb.append(book.getTitle()).append(" by ").append(book.getAuthor().getFirstName()).append(" ")
                                    .append(book.getAuthor().getLastName()).append("\n");
                        }
                        resultArea.setText(sb.toString());
                    } else {
                        resultArea.setText("No se encontraron libros con ese título.");
                    }
                }
            });

            panel.add(titleLabel);
            panel.add(titleField);
            panel.add(searchButton);
            panel.add(resultArea);

            frame.setVisible(true);
        });
    }

    static class Book {
        private String title;
        private Author author;

        public Book(String title, Author author) {
            this.title = title;
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Author getAuthor() {
            return author;
        }

        public void setAuthor(Author author) {
            this.author = author;
        }
    }

    static class Author {
        private String firstName;
        private String lastName;

        public Author(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }

    static class BookService {
        private List<Book> bookList; // Supongamos que tienes una lista de libros en tu servicio

        public BookService() {
            // Inicialización de la lista de libros
            bookList = new ArrayList<>();
            bookList.add(new Book("Don Quijote", new Author("Miguel", "de Cervantes")));
            bookList.add(new Book("Orgullo y Prejuicio", new Author("Jane", "Austen")));
            // Agrega más libros según tu base de datos o lógica de la aplicación
        }

        public List<Book> findBooksByTitle(String title) {
            List<Book> result = new ArrayList<>();
            for (Book book : bookList) {
                if (book.getTitle().equalsIgnoreCase(title)) {
                    result.add(book);
                }
            }
            return result;
        }
    }
}
