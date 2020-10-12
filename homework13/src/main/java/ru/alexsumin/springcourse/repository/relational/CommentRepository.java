package ru.alexsumin.springcourse.repository.relational;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexsumin.springcourse.domain.relational.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByBookId(Long bookId);
}
