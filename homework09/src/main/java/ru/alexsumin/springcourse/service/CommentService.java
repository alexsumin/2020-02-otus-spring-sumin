package ru.alexsumin.springcourse.service;

import ru.alexsumin.springcourse.domain.Comment;

import java.util.List;

public interface CommentService {
    Comment save(Comment comment);
    List<Comment> findAll();
    List<Comment> commentsOfBook(Long bookId);
}
