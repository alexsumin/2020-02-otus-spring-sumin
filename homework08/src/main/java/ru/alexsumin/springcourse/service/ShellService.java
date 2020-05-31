package ru.alexsumin.springcourse.service;

public interface ShellService {
    String list(String entity);
    String addAuthor(String name);
    String addGenre(String name);
    String addBook(String title, String published, String author,  String genre);
    String deleteBook(String bookId);
    String updateBook(String bookId, String title, String published, String author, String genre);
    String showCommentsForBook(String bookId);
    String addComment(String bookId, String text);
}
