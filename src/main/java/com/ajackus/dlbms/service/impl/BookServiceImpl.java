package com.ajackus.dlbms.service.impl;

import com.ajackus.dlbms.dto.BookRequestDto;
import com.ajackus.dlbms.dto.BookResponseDto;
import com.ajackus.dlbms.entity.Status;
import com.ajackus.dlbms.model.Book;
import com.ajackus.dlbms.repository.BookRepository;
import com.ajackus.dlbms.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public BookResponseDto createBook(BookRequestDto bookRequestDto) {
        Book book = new Book();
        book.setBookId(bookRequestDto.getBookId());
        book.setTitle(bookRequestDto.getTitle());
        book.setAuthor(bookRequestDto.getAuthor());
        book.setGenre(bookRequestDto.getGenre());
        book.setAvailabilityStatus(Status.valueOf(bookRequestDto.getAvailabilityStatus()));
        book.setUuid(UUID.randomUUID().toString().substring(0, 8));

        Optional<Book> savedBook = Optional.of(bookRepository.save(book));

        return savedBook.map(myBook -> {
            BookResponseDto bookResponseDto = new BookResponseDto();
            bookResponseDto.setBookId(myBook.getBookId());
            bookResponseDto.setUuid(myBook.getUuid());
            bookResponseDto.setTitle(myBook.getTitle());
            bookResponseDto.setAuthor(myBook.getAuthor());
            bookResponseDto.setGenre(myBook.getGenre());
            bookResponseDto.setAvailabilityStatus(myBook.getAvailabilityStatus());
            return bookResponseDto;
        }).orElse(null);
    }
}
