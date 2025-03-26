package com.ajackus.dlbms.service;

import com.ajackus.dlbms.dto.BookRequestDto;
import com.ajackus.dlbms.dto.BookResponseDto;

import java.util.List;

public interface BookService {

    BookResponseDto createBook(BookRequestDto bookRequestDto);

    BookResponseDto updateBookByBookId(String bookId, BookRequestDto bookRequestDto);

    BookResponseDto deleteBookByBookId(String bookId);

    BookResponseDto getBookByBookIdOrTitle(String bookIdOrTitle);

    List<BookResponseDto> getAllBooks();

}
