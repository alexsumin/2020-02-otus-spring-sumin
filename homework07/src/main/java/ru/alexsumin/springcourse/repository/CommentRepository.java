package ru.alexsumin.springcourse.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.alexsumin.springcourse.domain.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    Comment save(Comment comment);
    List<Comment> findAll();
    List<Comment> findAllByBookId(Long bookId);
}
