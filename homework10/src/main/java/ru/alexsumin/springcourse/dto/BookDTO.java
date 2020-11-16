package ru.alexsumin.springcourse.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    Long id;
    String title;
    Date published;
    AuthorDTO author;
    GenreDTO genre;
}
