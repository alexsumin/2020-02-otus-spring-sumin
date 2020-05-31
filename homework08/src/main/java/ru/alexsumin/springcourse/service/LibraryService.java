package ru.alexsumin.springcourse.service;

public interface LibraryService {
    String list(String entity);
    String saveAuthor(String name);
    String saveGenre(String name);
    String saveBook(String title, String author, String published, String genre);
    String deleteBook(String bookId);
    String updateBook(String bookId, String title, String published, String author, String genre);
    String addComment(String bookId, String text);
    String commentsOfBook(String bookId);
}
