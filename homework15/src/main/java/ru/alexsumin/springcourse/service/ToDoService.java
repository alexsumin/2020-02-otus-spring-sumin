package ru.alexsumin.springcourse.service;

import ru.alexsumin.springcourse.domain.ToDo;
import ru.alexsumin.springcourse.dto.ToDoDTO;

public interface ToDoService {
    ToDo create(ToDoDTO toDoDTO);
}
