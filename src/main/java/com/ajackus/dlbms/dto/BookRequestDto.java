package com.ajackus.dlbms.dto;

import lombok.Data;

@Data
public class BookRequestDto {

    private String bookId;

    private String title;

    private String author;

    private String genre;

    private String availabilityStatus;
}
