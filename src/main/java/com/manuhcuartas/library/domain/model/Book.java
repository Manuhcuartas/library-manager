package com.manuhcuartas.library.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class Book {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private BookStatus status;
    private Long borrowedByUserId;
    private LocalDateTime lastBorrowedDate;

    public static Book create(String title, String author, String isbn) {
        return Book.builder()
                .title(title)
                .author(author)
                .isbn(isbn)
                .status(BookStatus.AVAILABLE)
                .build();
    }


    public void borrow(Long userId) {
        if (this.status == BookStatus.BORROWED) {
            throw new IllegalStateException("El libro ya está prestado y no se puede prestar de nuevo.");
        }

        this.status = BookStatus.BORROWED;
        this.borrowedByUserId = userId;
        this.lastBorrowedDate = LocalDateTime.now();
    }

    public void returnBook() {
        if (this.status == BookStatus.AVAILABLE) {
            throw new IllegalStateException("El libro no estaba prestado.");
        }

        this.status = BookStatus.AVAILABLE;
        this.borrowedByUserId = null;
    }

    public boolean isAvailable() {
        return this.status == BookStatus.AVAILABLE;
    }
}