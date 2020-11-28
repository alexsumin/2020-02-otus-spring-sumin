package ru.alexsumin.springcourse.mapper;

import org.mapstruct.Mapper;
import ru.alexsumin.springcourse.domain.Author;
import ru.alexsumin.springcourse.dto.AuthorDTO;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author toAuthor(AuthorDTO authorDTO);
    AuthorDTO toDTO(Author author);
}
