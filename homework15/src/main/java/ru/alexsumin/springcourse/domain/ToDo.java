package ru.alexsumin.springcourse.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "TODOS")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;
    @Column(name = "text")
    String text;
    @Column(name = "assigner")
    String assigner;
    @Column(name = "is_finished")
    Boolean isFinished;
}
