package ru.alexsumin.springcourse.repository.impl;

import org.springframework.stereotype.Repository;
import ru.alexsumin.springcourse.domain.Author;
import ru.alexsumin.springcourse.repository.AuthorRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Author save(Author author) {
        if (Objects.isNull(author.getId())) {
            entityManager.persist(author);
            return author;
        }
        return entityManager.merge(author);
    }

    @Override
    public List<Author> findAll() {
        return entityManager
                .createQuery("select a from Author a", Author.class)
                .getResultList();
    }

    @Override
    public Optional<Author> findByName(String name) {
        List<Author> authors = entityManager.createQuery("select a from Author a where name = :name", Author.class)
                .setParameter("name", name)
                .getResultList();
        if (authors.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(authors.get(0));
    }
}
