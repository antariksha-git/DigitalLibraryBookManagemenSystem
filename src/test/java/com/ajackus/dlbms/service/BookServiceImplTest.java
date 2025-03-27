package com.ajackus.dlbms.service;

import com.ajackus.dlbms.dto.BookRequestDto;
import com.ajackus.dlbms.dto.BookResponseDto;
import com.ajackus.dlbms.excpetion.BookAlreadyExistException;
import com.ajackus.dlbms.excpetion.BookNotFoundException;
import com.ajackus.dlbms.mapper.BookMapper;
import com.ajackus.dlbms.model.Book;
import com.ajackus.dlbms.repository.BookRepository;
import com.ajackus.dlbms.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateBook() {
        BookRequestDto bookRequestDto = new BookRequestDto();
        bookRequestDto.setBookId("1");
        bookRequestDto.setTitle("Test Book");

        Book book = new Book();
        BookResponseDto bookResponseDto = new BookResponseDto();

        when(bookRepository.findByBookIdOrTitle(anyString(), anyString())).thenReturn(Optional.empty());
        when(bookMapper.toEntity(any(BookRequestDto.class))).thenReturn(book);
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        when(bookMapper.toDto(any(Book.class))).thenReturn(bookResponseDto);

        BookResponseDto result = bookService.createBook(bookRequestDto);
        assertEquals(bookResponseDto, result);
    }

    @Test
    public void testCreateBookAlreadyExists() {
        BookRequestDto bookRequestDto = new BookRequestDto();
        bookRequestDto.setBookId("1");
        bookRequestDto.setTitle("Test Book");

        Book book = new Book();

        when(bookRepository.findByBookIdOrTitle(anyString(), anyString())).thenReturn(Optional.of(book));

        assertThrows(BookAlreadyExistException.class, () -> bookService.createBook(bookRequestDto));
    }

    @Test
    public void testUpdateBookByBookId() {
        BookRequestDto bookRequestDto = new BookRequestDto();
        bookRequestDto.setTitle("Updated Book");

        Book book = new Book();
        BookResponseDto bookResponseDto = new BookResponseDto();

        when(bookRepository.findByBookId(anyString())).thenReturn(Optional.of(book));
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        when(bookMapper.toDto(any(Book.class))).thenReturn(bookResponseDto);

        BookResponseDto result = bookService.updateBookByBookId("1", bookRequestDto);
        assertEquals(bookResponseDto, result);
    }

    @Test
    public void testUpdateBookByBookIdNotFound() {
        BookRequestDto bookRequestDto = new BookRequestDto();

        when(bookRepository.findByBookId(anyString())).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> bookService.updateBookByBookId("1", bookRequestDto));
    }

    @Test
    public void testDeleteBookByBookId() {
        Book book = new Book();
        BookResponseDto bookResponseDto = new BookResponseDto();

        when(bookRepository.findByBookId(anyString())).thenReturn(Optional.of(book));
        doNothing().when(bookRepository).delete(any(Book.class));
        when(bookMapper.toDto(any(Book.class))).thenReturn(bookResponseDto);

        BookResponseDto result = bookService.deleteBookByBookId("1");
        assertEquals(bookResponseDto, result);
    }

    @Test
    public void testDeleteBookByBookIdNotFound() {
        when(bookRepository.findByBookId(anyString())).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> bookService.deleteBookByBookId("1"));
    }

    @Test
    public void testGetBookByBookIdOrTitle() {
        Book book = new Book();
        BookResponseDto bookResponseDto = new BookResponseDto();

        when(bookRepository.findByBookIdOrTitle(anyString(), anyString())).thenReturn(Optional.of(book));
        when(bookMapper.toDto(any(Book.class))).thenReturn(bookResponseDto);

        BookResponseDto result = bookService.getBookByBookIdOrTitle("1");
        assertEquals(bookResponseDto, result);
    }

    @Test
    public void testGetBookByBookIdOrTitleNotFound() {
        when(bookRepository.findByBookIdOrTitle(anyString(), anyString())).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> bookService.getBookByBookIdOrTitle("1"));
    }

    @Test
    public void testGetAllBooks() {
        Book book = new Book();
        BookResponseDto bookResponseDto = new BookResponseDto();

        when(bookRepository.findAll()).thenReturn(Collections.singletonList(book));
        when(bookMapper.toDto(any(Book.class))).thenReturn(bookResponseDto);

        List<BookResponseDto> result = bookService.getAllBooks();
        assertEquals(1, result.size());
        assertEquals(bookResponseDto, result.get(0));
    }
}