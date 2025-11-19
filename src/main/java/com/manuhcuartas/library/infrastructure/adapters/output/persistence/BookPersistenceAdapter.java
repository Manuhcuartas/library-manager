package com.manuhcuartas.library.infrastructure.adapters.output.persistence;

import com.manuhcuartas.library.domain.model.Book;
import com.manuhcuartas.library.domain.ports.output.BookRepositoryPort;
import com.manuhcuartas.library.infrastructure.adapters.output.persistence.entity.BookEntity;
import com.manuhcuartas.library.infrastructure.adapters.output.persistence.mapper.BookPersistenceMapper;
import com.manuhcuartas.library.infrastructure.adapters.output.persistence.repository.SpringDataBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookPersistenceAdapter implements BookRepositoryPort {

    private final SpringDataBookRepository repository;
    private final BookPersistenceMapper mapper;

    @Override
    public Book save(Book book) {
        BookEntity entity = mapper.toEntity(book);
        BookEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Book> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}