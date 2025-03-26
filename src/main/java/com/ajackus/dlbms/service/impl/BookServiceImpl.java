package com.ajackus.dlbms.service.impl;

import com.ajackus.dlbms.dto.BookRequestDto;
import com.ajackus.dlbms.dto.BookResponseDto;
import com.ajackus.dlbms.enums.Status;
import com.ajackus.dlbms.excpetion.BookAlreadyExistException;
import com.ajackus.dlbms.excpetion.BookNotFoundException;
import com.ajackus.dlbms.excpetion.FieldValidationException;
import com.ajackus.dlbms.mapper.BookMapper;
import com.ajackus.dlbms.model.Book;
import com.ajackus.dlbms.repository.BookRepository;
import com.ajackus.dlbms.service.BookService;
import com.ajackus.dlbms.utils.CommonUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookResponseDto createBook(BookRequestDto bookRequestDto) {
        Book existedBookByBookIdOrTitle = bookRepository.findByBookIdOrTitle(bookRequestDto.getBookId(), bookRequestDto.getTitle())
                .orElse(null);

        if(!CommonUtils.isNullOrEmpty(existedBookByBookIdOrTitle)) {
            throw new BookAlreadyExistException("Book already exist with id: " + bookRequestDto.getBookId() + " or title: " + bookRequestDto.getTitle());
        }

        Book book = bookMapper.toEntity(bookRequestDto);
        book.setUuid(UUID.randomUUID().toString().substring(0, 8));

        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public BookResponseDto updateBookByBookId(String bookId, BookRequestDto bookRequestDto) {
        if(CommonUtils.isNullOrEmpty(bookId)) {
            throw new FieldValidationException("Book id is required");
        }

        Book book = bookRepository.findByBookId(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + bookId));

        if(!CommonUtils.isNullOrEmpty(bookRequestDto.getTitle())) {
            book.setTitle(bookRequestDto.getTitle());
        }
        if(!CommonUtils.isNullOrEmpty(bookRequestDto.getAuthor())) {
            book.setAuthor(bookRequestDto.getAuthor());
        }
        if(!CommonUtils.isNullOrEmpty(bookRequestDto.getGenre())) {
            book.setGenre(bookRequestDto.getGenre());
        }
        if(bookRequestDto.getAvailabilityStatus() != null) {
            book.setAvailabilityStatus(Status.valueOf(bookRequestDto.getAvailabilityStatus()));
        }

        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public BookResponseDto deleteBookByBookId(String bookId) {
        if(CommonUtils.isNullOrEmpty(bookId)) {
            throw new RuntimeException();
        }

        Book book = bookRepository.findByBookId(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + bookId));

        bookRepository.delete(book);
        return bookMapper.toDto(book);
    }

    @Override
    public BookResponseDto getBookByBookIdOrTitle(String bookIdOrTitle) {
        if(CommonUtils.isNullOrEmpty(bookIdOrTitle)) {
            throw new RuntimeException();
        }

        Book book = bookRepository.findByBookIdOrTitle(bookIdOrTitle, bookIdOrTitle)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id or title: " + bookIdOrTitle));

        return bookMapper.toDto(book);
    }

    @Override
    public List<BookResponseDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }

}
