package ru.alexsumin.springcourse.service;

import ru.alexsumin.springcourse.domain.Book;

import java.util.List;

public interface BookService {
    Book save(Book book);
    List<Book> findAll();
    void deleteById(Long id);
}
