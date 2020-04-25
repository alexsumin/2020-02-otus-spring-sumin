package ru.alexsumin.springcourse.service;

public interface ShellService {
    String list(String entity);
    String addAuthor(String name);
    String addGenre(String name);
    String addBook(String title, String published, String author,  String genre);
    String deleteBook(Long id);
    String updateBook(Long id, String title, String published, String author, String genre);
    String showCommentsForBook(Long bookId);
    String addComment(Long bookId, String text);
}
