package ru.alexsumin.springcourse.mapper;

import org.mapstruct.Mapper;
import ru.alexsumin.springcourse.domain.Genre;
import ru.alexsumin.springcourse.dto.GenreDTO;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    Genre toGenre(GenreDTO genreDTO);
    GenreDTO toDTO(Genre genre);
}
