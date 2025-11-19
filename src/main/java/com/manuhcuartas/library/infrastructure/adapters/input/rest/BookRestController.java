package com.manuhcuartas.library.infrastructure.adapters.input.rest;

import com.manuhcuartas.library.domain.model.Book;
import com.manuhcuartas.library.domain.service.BookService;
import com.manuhcuartas.library.infrastructure.adapters.input.rest.mapper.BookRestMapper;
import com.manuhcuartas.library.infrastructure.adapters.input.rest.model.BookResponse;
import com.manuhcuartas.library.infrastructure.adapters.input.rest.model.CreateBookRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
@Tag(name = "Books", description = "Library Book Management APIs")
public class BookRestController {

    private final BookService bookService;
    private final BookRestMapper mapper;

    @PostMapping
    @Operation(summary = "Create a new book")
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody CreateBookRequest request) {
        Book book = mapper.toDomain(request);
        Book savedBook = bookService.createBook(book);
        return new ResponseEntity<>(mapper.toResponse(savedBook), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get book by ID")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id) {
        // Nota: Aquí deberíamos manejar la excepción si no existe (try-catch o ControllerAdvice)
        // Para este ejemplo rápido, asumimos que el service lanza RuntimeException y devuelve 500 (se puede mejorar luego)
        return bookService.getAllBooks().stream() // Truco rápido si no expusimos findById en service
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .map(book -> ResponseEntity.ok(mapper.toResponse(book)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Get all books")
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        List<BookResponse> responses = bookService.getAllBooks().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PostMapping("/{id}/borrow")
    @Operation(summary = "Borrow a book")
    public ResponseEntity<BookResponse> borrowBook(@PathVariable Long id, @RequestParam Long userId) {
        Book borrowedBook = bookService.borrowBook(id, userId);
        return ResponseEntity.ok(mapper.toResponse(borrowedBook));
    }

    @PostMapping("/{id}/return")
    @Operation(summary = "Return a book")
    public ResponseEntity<BookResponse> returnBook(@PathVariable Long id) {
        Book returnedBook = bookService.returnBook(id);
        return ResponseEntity.ok(mapper.toResponse(returnedBook));
    }
}