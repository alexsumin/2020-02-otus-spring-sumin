package ru.alexsumin.springcourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexsumin.springcourse.domain.ToDo;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
}
