package ru.alexsumin.springcourse.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "BOOKS")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"comments"})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;
    @Column(name = "TITLE")
    String title;
    @Column(name = "PUBLISHED")
    Date published;
    @OneToOne(targetEntity = Author.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "AUTHOR_ID")
    Author author;
    @OneToOne(targetEntity = Genre.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "GENRE_ID")
    Genre genre;
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ID")
    Set<Comment> comments;
}
