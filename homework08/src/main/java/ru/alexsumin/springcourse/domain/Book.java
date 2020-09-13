package ru.alexsumin.springcourse.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Set;

@Document("books")
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "comments")
@EqualsAndHashCode(exclude = "comments")
public class Book {
    @Id
    String id;
    String title;
    LocalDate published;
    @DBRef
    Author author;
    @DBRef
    Genre genre;
    Set<String> comments;
}
