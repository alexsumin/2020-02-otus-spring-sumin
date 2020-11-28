package ru.alexsumin.springcourse.mapper;

import org.mapstruct.Mapper;
import ru.alexsumin.springcourse.domain.ToDo;
import ru.alexsumin.springcourse.dto.ToDoDTO;

@Mapper(componentModel = "spring")
public interface ToDoMapper {
    ToDo toEntity(ToDoDTO toDoDTO);
    ToDoDTO toDTO(ToDo genre);
}
