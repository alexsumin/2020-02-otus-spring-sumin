package ru.alexsumin.springcourse.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "COMMENTS")
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "id")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;
    @Column(name = "TEXT", nullable = false)
    String text;
    @Column(name = "BOOK_ID", nullable = false)
    Long bookId;
}
