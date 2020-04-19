package ru.alexsumin.springcourse.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alexsumin.springcourse.domain.Comment;
import ru.alexsumin.springcourse.repository.CommentRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;

    @Transactional
    @Override
    public Comment save(Comment comment) {
        return repository.save(comment);
    }

    @Override
    public List<Comment> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Comment> commentsOfBook(Long bookId) {
        return repository.commentsOfBook(bookId);
    }
}
