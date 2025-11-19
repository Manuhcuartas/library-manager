package com.manuhcuartas.library.domain.ports.output;

import com.manuhcuartas.library.domain.model.Book;
import java.util.List;
import java.util.Optional;

public interface BookRepositoryPort {

    Book save(Book book);

    Optional<Book> findById(Long id);

    List<Book> findAll();
}