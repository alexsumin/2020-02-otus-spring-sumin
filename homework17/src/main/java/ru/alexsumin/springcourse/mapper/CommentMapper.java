package ru.alexsumin.springcourse.mapper;

import org.mapstruct.Mapper;
import ru.alexsumin.springcourse.domain.Comment;
import ru.alexsumin.springcourse.dto.CommentDTO;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment toComment(CommentDTO commentDTO);
    CommentDTO toDTO(Comment comment);
}
