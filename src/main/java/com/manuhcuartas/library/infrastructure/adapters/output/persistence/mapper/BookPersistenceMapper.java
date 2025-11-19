package com.manuhcuartas.library.infrastructure.adapters.output.persistence.mapper;

import com.manuhcuartas.library.domain.model.Book;
import com.manuhcuartas.library.infrastructure.adapters.output.persistence.entity.BookEntity;
import org.springframework.stereotype.Component;

@Component
public class BookPersistenceMapper {

    public BookEntity toEntity(Book book) {
        if (book == null) return null;
        return new BookEntity(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getStatus(),
                book.getBorrowedByUserId(),
                book.getLastBorrowedDate()
        );
    }

    public Book toDomain(BookEntity entity) {
        if (entity == null) return null;
        return new Book(
                entity.getId(),
                entity.getTitle(),
                entity.getAuthor(),
                entity.getIsbn(),
                entity.getStatus(),
                entity.getBorrowedByUserId(),
                entity.getLastBorrowedDate()
        );
    }
}