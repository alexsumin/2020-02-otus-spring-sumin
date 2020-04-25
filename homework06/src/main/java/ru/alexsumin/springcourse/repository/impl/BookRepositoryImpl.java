package ru.alexsumin.springcourse.repository.impl;

import org.springframework.stereotype.Repository;
import ru.alexsumin.springcourse.domain.Book;
import ru.alexsumin.springcourse.repository.BookRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class BookRepositoryImpl implements BookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Book save(Book book) {
        if (Objects.isNull(book.getId())) {
            entityManager.persist(book);
            return book;
        }
        return entityManager.merge(book);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Book.class, id));
    }

    @Override
    public List<Book> findAll() {
        return entityManager
                .createQuery("select b from Book b", Book.class)
                .getResultList();
    }

    @Override
    public void deleteById(Long id) {
        entityManager.createQuery("delete from Book where id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
