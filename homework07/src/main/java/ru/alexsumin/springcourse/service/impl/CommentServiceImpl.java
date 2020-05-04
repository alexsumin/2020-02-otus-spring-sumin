package ru.alexsumin.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alexsumin.springcourse.domain.Comment;
import ru.alexsumin.springcourse.repository.CommentRepository;
import ru.alexsumin.springcourse.service.CommentService;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentServiceImpl implements CommentService {

    CommentRepository repository;

    @Transactional
    @Override
    public Comment save(Comment comment) {
        return repository.save(comment);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Comment> commentsOfBook(Long bookId) {
        return repository.findAllByBookId(bookId);
    }
}
