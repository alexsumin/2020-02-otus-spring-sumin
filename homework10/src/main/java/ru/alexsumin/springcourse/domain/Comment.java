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
@ToString(exclude = "book")
@EqualsAndHashCode(exclude = "book")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;
    @Column(name = "TEXT", nullable = false)
    String text;
    @ManyToOne
    Book book;
}
