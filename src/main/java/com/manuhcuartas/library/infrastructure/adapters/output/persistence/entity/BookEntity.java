package com.manuhcuartas.library.infrastructure.adapters.output.persistence.entity;

import com.manuhcuartas.library.domain.model.BookStatus;
import jakarta.persistence.*; // Asegúrate de importar jakarta.persistence.*
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String isbn;

    @Enumerated(EnumType.STRING)
    private BookStatus status;

    private Long borrowedByUserId;

    private LocalDateTime lastBorrowedDate;
}