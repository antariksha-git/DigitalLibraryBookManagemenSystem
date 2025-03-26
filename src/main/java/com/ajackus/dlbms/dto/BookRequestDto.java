package com.ajackus.dlbms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class BookRequestDto {

    @NotNull(message = "Book Id cannot be null")
    @NotBlank(message = "Book Id cannot be blank")
    private String bookId;

    @NotNull(message = "Book title cannot be null")
    @NotBlank(message = "Book title cannot be blank")
    private String title;

    @NotNull(message = "Book's author cannot be null")
    @NotBlank(message = "Book's author cannot be blank")
    private String author;

    private String genre;

    @Pattern(regexp = "AVAILABLE|CHECKED_OUT", message = "Availability status should be either AVAILABLE or CHECKED_OUT")
    private String availabilityStatus;
}
