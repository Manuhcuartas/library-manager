package com.manuhcuartas.library.domain.service;

import com.manuhcuartas.library.domain.model.Book;
import com.manuhcuartas.library.domain.ports.output.BookRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class BookService {

    private final BookRepositoryPort bookRepository;

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book borrowBook(Long bookId, Long userId) {
        // 1. Buscamos el libro (usando el puerto)
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // 2. Ejecutamos la lógica de negocio (el libro decide si se puede prestar)
        book.borrow(userId);

        // 3. Guardamos el estado actualizado
        return bookRepository.save(book);
    }

    public Book returnBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        book.returnBook();

        return bookRepository.save(book);
    }
}