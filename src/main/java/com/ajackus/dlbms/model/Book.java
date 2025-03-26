package com.ajackus.dlbms.model;

import com.ajackus.dlbms.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "books")
@Data
public class Book {

    @Id
    @Column(name = "uuid")
    private String uuid;

    @Column(name = "book_id", unique = true)
    private String bookId;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "genre")
    private String genre;

    @Column(name = "availability_status")
    @Enumerated(EnumType.STRING)
    private Status availabilityStatus;
}
