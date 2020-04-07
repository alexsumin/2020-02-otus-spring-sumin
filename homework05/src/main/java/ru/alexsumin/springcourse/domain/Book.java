package ru.alexsumin.springcourse.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book {
    Long id;
    String title;
    Date published;
    Author author;
    Genre genre;
}
