package com.ajackus.dlbms.mapper;

import com.ajackus.dlbms.dto.BookResponseDto;
import com.ajackus.dlbms.dto.BookRequestDto;
import com.ajackus.dlbms.model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    Book toEntity(BookRequestDto bookRequestDto);

    BookResponseDto toDto(Book book);
}
