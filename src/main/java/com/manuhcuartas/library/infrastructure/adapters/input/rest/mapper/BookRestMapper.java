package com.manuhcuartas.library.infrastructure.adapters.input.rest.mapper;

import com.manuhcuartas.library.domain.model.Book;
import com.manuhcuartas.library.infrastructure.adapters.input.rest.model.BookResponse;
import com.manuhcuartas.library.infrastructure.adapters.input.rest.model.CreateBookRequest;
import org.springframework.stereotype.Component;

@Component
public class BookRestMapper {

    public Book toDomain(CreateBookRequest request) {
        return Book.create(
                request.title(),
                request.author(),
                request.isbn()
        );
    }

    public BookResponse toResponse(Book book) {
        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getStatus().name(),
                book.getLastBorrowedDate()
        );
    }
}