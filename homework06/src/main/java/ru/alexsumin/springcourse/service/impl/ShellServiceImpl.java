package ru.alexsumin.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.alexsumin.springcourse.service.LibraryService;
import ru.alexsumin.springcourse.service.ShellService;

@ShellComponent
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShellServiceImpl implements ShellService {
    LibraryService libraryService;

    @Override
    @ShellMethod(key = "list", value = "List of all entity instances.\n" +
            "           Examples:\n" +
            "               list authors\n" +
            "               list books\n" +
            "               list genres\n" +
            "               list comments")
    public String list(@ShellOption({"entity"}) String entity) {
        return libraryService.list(entity);
    }

    @Override
    @ShellMethod(key = "add_author", value = "Add author. \n" +
            "           Example:\n" +
            "               add_author Pushkin")
    public String addAuthor(@ShellOption({"name"}) String name) {
        return libraryService.saveAuthor(name);
    }

    @Override
    @ShellMethod(key = "add_genre", value = "Add genre. Args: genre\n" +
            "           Example:\n" +
            "               add_genre Non-fiction")
    public String addGenre(@ShellOption({"name"}) String name) {
        return libraryService.saveGenre(name);
    }

    @Override
    @ShellMethod(key = "add_book", value = "Add book. Args: title, author, published, genre\n" +
            "           Example:\n" +
            "               add_book 'The Captains Daughter' '1836-01-01' 'Pushkin' 'Historical novel'")
    public String addBook(@ShellOption({"title"}) String title,
                          @ShellOption({"published"}) String published,
                          @ShellOption({"author"}) String author,
                          @ShellOption({"genre"}) String genre) {
        return libraryService.saveBook(title, author, published, genre);
    }

    @Override
    @ShellMethod(key = "delete_book", value = "Delete book. Args: id\n" +
            "           Example:\n" +
            "               delete_book 42")
    public String deleteBook(@ShellOption({"id"}) Long id) {
        return libraryService.deleteBook(id);
    }

    @Override
    @ShellMethod(key = "update_book", value = "Update book. Args: id, title, published, author, genre\n" +
            "           Example:\n" +
            "               update_book 1 'The Captains Daughter' '1836-01-01' 'Pushkin' 'Historical novel'")
    public String updateBook(@ShellOption({"id"}) Long id,
                             @ShellOption({"title"}) String title,
                             @ShellOption({"published"}) String published,
                             @ShellOption({"author"}) String author,
                             @ShellOption({"genre"}) String genre) {
        return libraryService.updateBook(id, title, published, author, genre);
    }

    @Override
    @ShellMethod(key = "show_comments_for_book", value = "Show comments of book. Args: id, text\n" +
            "           Example:\n" +
            "               show_comments_for_book 1")
    public String showCommentsForBook(Long bookId) {
        return libraryService.commentsOfBook(bookId);
    }

    @Override
    @ShellMethod(key = "add_comment", value = "Add comment to book. Args: id, text\n" +
            "           Example:\n" +
            "               add_comment 1 'Comment'")
    public String addComment(Long bookId, String text) {
        return libraryService.addComment(bookId, text);
    }
}
