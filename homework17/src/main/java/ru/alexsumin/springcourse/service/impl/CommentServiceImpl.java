package ru.alexsumin.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alexsumin.springcourse.domain.Book;
import ru.alexsumin.springcourse.domain.Comment;
import ru.alexsumin.springcourse.repository.CommentRepository;
import ru.alexsumin.springcourse.service.BookService;
import ru.alexsumin.springcourse.service.CommentService;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentServiceImpl implements CommentService {

    CommentRepository repository;
    BookService bookService;

    @Transactional
    @Override
    public void create(Long bookId, Comment comment) {
        Book book = bookService.findById(bookId);
        comment.setBook(book);
        repository.save(comment);
    }

    @Transactional
    @Override
    public void save(Comment comment) {
        repository.save(comment);
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
