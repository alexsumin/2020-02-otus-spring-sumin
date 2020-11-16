package ru.alexsumin.springcourse.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.*;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "BOOKS")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "comments")
@EqualsAndHashCode(exclude = "comments")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;
    @Column(name = "TITLE")
    String title;
    @Column(name = "PUBLISHED")
    Date published;
    @ManyToOne(targetEntity = Author.class, cascade = {REFRESH, MERGE, PERSIST})
    @JoinColumn(name = "AUTHOR_ID")
    Author author;
    @ManyToOne(targetEntity = Genre.class, cascade = {REFRESH, MERGE, PERSIST})
    @JoinColumn(name = "GENRE_ID")
    Genre genre;
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(targetEntity = Comment.class, cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ID", updatable = false)
    Set<Comment> comments = new HashSet<>();
}
