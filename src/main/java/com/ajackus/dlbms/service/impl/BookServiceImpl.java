package com.ajackus.dlbms.service.impl;

import com.ajackus.dlbms.dto.BookRequestDto;
import com.ajackus.dlbms.dto.BookResponseDto;
import com.ajackus.dlbms.mapper.BookMapper;
import com.ajackus.dlbms.model.Book;
import com.ajackus.dlbms.repository.BookRepository;
import com.ajackus.dlbms.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookResponseDto createBook(BookRequestDto bookRequestDto) {
        Book book = bookMapper.toEntity(bookRequestDto);
        book.setUuid(UUID.randomUUID().toString().substring(0, 8));

        return bookMapper.toDto(bookRepository.save(book));
    }
}
