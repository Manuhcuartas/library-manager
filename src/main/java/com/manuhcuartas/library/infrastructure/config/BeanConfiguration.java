package com.manuhcuartas.library.infrastructure.config;

import com.manuhcuartas.library.domain.ports.output.BookRepositoryPort;
import com.manuhcuartas.library.domain.service.BookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public BookService bookService(BookRepositoryPort bookRepositoryPort) {
        return new BookService(bookRepositoryPort);
    }
}