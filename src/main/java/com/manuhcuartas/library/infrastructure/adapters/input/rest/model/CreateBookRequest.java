package com.manuhcuartas.library.infrastructure.adapters.input.rest.model;

import jakarta.validation.constraints.NotBlank;

public record CreateBookRequest(
        @NotBlank(message = "Title cannot be empty")
        String title,

        @NotBlank(message = "Author cannot be empty")
        String author,

        @NotBlank(message = "ISBN cannot be empty")
        String isbn
) {}