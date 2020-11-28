package ru.alexsumin.springcourse.domain.relational;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(exclude = "id")
@Entity
@Table(name = "AUTHORS")
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;
    @Column(name = "NAME", nullable = false, unique = true)
    String name;
}
