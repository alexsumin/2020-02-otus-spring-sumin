package ru.alexsumin.springcourse.mapper;

import org.mapstruct.Mapper;
import ru.alexsumin.springcourse.domain.Book;
import ru.alexsumin.springcourse.dto.BookDTO;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book toBook(BookDTO bookDTO);
    BookDTO toDTO(Book book);
}
