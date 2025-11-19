package com.manuhcuartas.library.domain.service;

import com.manuhcuartas.library.domain.model.Book;
import com.manuhcuartas.library.domain.model.BookStatus;
import com.manuhcuartas.library.domain.ports.output.BookRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Habilita Mockito
class BookServiceTest {

    @Mock
    private BookRepositoryPort bookRepository; // Simulamos el puerto (no hay base de datos real)

    @InjectMocks
    private BookService bookService; // Inyectamos el mock en el servicio

    @Test
    void should_borrow_book_successfully_when_available() {
        // GIVEN (Dado un libro disponible)
        Long bookId = 1L;
        Long userId = 123L;
        Book availableBook = Book.builder()
                .id(bookId)
                .status(BookStatus.AVAILABLE)
                .build();

        // Enseñamos al Mock qué devolver cuando le pregunten
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(availableBook));
        when(bookRepository.save(any(Book.class))).thenAnswer(invocation -> invocation.getArgument(0)); // Devuelve lo que guarda

        // WHEN (Cuando lo prestamos)
        Book result = bookService.borrowBook(bookId, userId);

        // THEN (Entonces el estado debe cambiar)
        assertEquals(BookStatus.BORROWED, result.getStatus());
        assertEquals(userId, result.getBorrowedByUserId());
        verify(bookRepository).save(availableBook);
    }

    @Test
    void should_throw_exception_when_book_already_borrowed() {
        // GIVEN (Dado un libro YA prestado)
        Long bookId = 1L;
        Book borrowedBook = Book.builder()
                .id(bookId)
                .status(BookStatus.BORROWED)
                .build();

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(borrowedBook));

        assertThrows(IllegalStateException.class, () -> {
            bookService.borrowBook(bookId, 999L);
        });

        verify(bookRepository, never()).save(any());
    }
}