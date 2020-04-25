package ru.alexsumin.springcourse.repository;

import ru.alexsumin.springcourse.domain.Comment;

import java.util.List;

public interface CommentRepository {
    Comment save(Comment comment);
    List<Comment> findAll();
    List<Comment> commentsOfBook(Long bookId);
}
