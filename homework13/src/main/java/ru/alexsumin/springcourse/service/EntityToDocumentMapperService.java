package ru.alexsumin.springcourse.service;

import ru.alexsumin.springcourse.domain.document.AuthorDocument;
import ru.alexsumin.springcourse.domain.document.BookDocument;
import ru.alexsumin.springcourse.domain.document.GenreDocument;
import ru.alexsumin.springcourse.domain.relational.Author;
import ru.alexsumin.springcourse.domain.relational.Book;
import ru.alexsumin.springcourse.domain.relational.Genre;

public interface EntityToDocumentMapperService {
    AuthorDocument toDocument(Author author);
    GenreDocument toDocument(Genre genre);
    BookDocument toDocument(Book book);
}
