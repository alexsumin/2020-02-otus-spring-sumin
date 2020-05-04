package ru.alexsumin.springcourse.service;

public interface LibraryService {
    String list(String entity);
    String saveAuthor(String name);
    String saveGenre(String name);
    String saveBook(String title, String author, String published, String genre);
    String deleteBook(long id);
    String updateBook(long id, String title, String published, String author, String genre);
    String addComment(Long bookId, String text);
    String commentsOfBook(Long bookId);
}
