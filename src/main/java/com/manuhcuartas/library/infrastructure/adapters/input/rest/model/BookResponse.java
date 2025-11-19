package com.manuhcuartas.library.infrastructure.adapters.input.rest.model;

import java.time.LocalDateTime;

public record BookResponse(
        Long id,
        String title,
        String author,
        String isbn,
        String status,
        LocalDateTime lastBorrowedDate
) {
}