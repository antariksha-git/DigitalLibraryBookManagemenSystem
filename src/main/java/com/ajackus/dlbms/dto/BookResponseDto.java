package com.ajackus.dlbms.dto;

import com.ajackus.dlbms.enums.Status;
import lombok.Data;

@Data
public class BookResponseDto {

    private String uuid;

    private String bookId;

    private String title;

    private String author;

    private String genre;

    private Status availabilityStatus;
}
