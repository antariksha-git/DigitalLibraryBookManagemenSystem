package com.ajackus.dlbms.controller;

import com.ajackus.dlbms.dto.BookRequestDto;
import com.ajackus.dlbms.dto.BookResponseDto;
import com.ajackus.dlbms.service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@AllArgsConstructor
public class BookController {

    private BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponseDto> createBook(@RequestBody @Valid BookRequestDto bookRequestDto) {
        BookResponseDto bookResponseDto = bookService.createBook(bookRequestDto);
        return ResponseEntity.ok(bookResponseDto);
    }

    @GetMapping("/{bookIdOrTitle}")
    public ResponseEntity<BookResponseDto> getBookByBookIdOrTitle(@PathVariable String bookIdOrTitle) {
        BookResponseDto bookResponseDto = bookService.getBookByBookIdOrTitle(bookIdOrTitle);
        return ResponseEntity.ok(bookResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDto>> getAllBooks() {
        List<BookResponseDto> bookResponseDtos = bookService.getAllBooks();
        return ResponseEntity.ok(bookResponseDtos);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<BookResponseDto> updateBookByBookId(@PathVariable String bookId, @RequestBody BookRequestDto bookRequestDto) {
        BookResponseDto bookResponseDto = bookService.updateBookByBookId(bookId, bookRequestDto);
        return ResponseEntity.ok(bookResponseDto);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<BookResponseDto> deleteBookByBookId(@PathVariable String bookId) {
        BookResponseDto bookResponseDto = bookService.deleteBookByBookId(bookId);
        return ResponseEntity.ok(bookResponseDto);
    }
}
