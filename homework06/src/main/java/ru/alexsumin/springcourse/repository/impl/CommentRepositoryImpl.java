package ru.alexsumin.springcourse.repository.impl;

import org.springframework.stereotype.Repository;
import ru.alexsumin.springcourse.domain.Comment;
import ru.alexsumin.springcourse.repository.CommentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;

@Repository
public class CommentRepositoryImpl implements CommentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Comment save(Comment comment) {
        if (Objects.isNull(comment.getId())) {
            entityManager.persist(comment);
            return comment;
        }
        return entityManager.merge(comment);
    }

    @Override
    public List<Comment> findAll() {
        return entityManager
                .createQuery("select c from Comment c", Comment.class)
                .getResultList();
    }

    @Override
    public List<Comment> commentsOfBook(Long bookId) {
        TypedQuery<Comment> query = entityManager.createQuery("select c " +
                        "from Comment c " +
                        "where c.bookId = :bookId",
                Comment.class);
        query.setParameter("bookId", bookId);
        return query.getResultList();
    }
}
