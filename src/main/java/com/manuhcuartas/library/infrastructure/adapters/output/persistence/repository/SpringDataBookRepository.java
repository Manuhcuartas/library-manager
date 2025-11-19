package com.manuhcuartas.library.infrastructure.adapters.output.persistence.repository;

import com.manuhcuartas.library.infrastructure.adapters.output.persistence.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataBookRepository extends JpaRepository<BookEntity, Long> {
}