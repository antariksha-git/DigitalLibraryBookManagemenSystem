package com.ajackus.dlbms.service;

import com.ajackus.dlbms.dto.BookRequestDto;
import com.ajackus.dlbms.dto.BookResponseDto;

public interface BookService {

    BookResponseDto createBook(BookRequestDto bookRequestDto);

}
