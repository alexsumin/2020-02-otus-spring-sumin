package ru.alexsumin.springcourse.service;

import ru.alexsumin.springcourse.domain.Book;

import java.util.List;
import java.util.Set;

public interface BookService {
    Book save(Book book);
    List<Book> findAll();
    void deleteById(String id);
    void addComment(String id, String comment);
    Set<String> commentsOfBook(String id);
    Book update(Book book);
}
