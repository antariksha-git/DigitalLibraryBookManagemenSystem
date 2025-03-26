package com.ajackus.dlbms.repository;

import com.ajackus.dlbms.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, String> {
    Optional<Book> findByBookIdOrTitle(String bookId, String title);
    Optional<Book> findByBookId(String bookId);
}
