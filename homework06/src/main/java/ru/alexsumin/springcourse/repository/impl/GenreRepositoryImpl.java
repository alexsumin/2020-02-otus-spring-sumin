package ru.alexsumin.springcourse.repository.impl;

import org.springframework.stereotype.Repository;
import ru.alexsumin.springcourse.domain.Genre;
import ru.alexsumin.springcourse.repository.GenreRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class GenreRepositoryImpl implements GenreRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Genre save(Genre genre) {
        if (Objects.isNull(genre.getId())) {
            entityManager.persist(genre);
            return genre;
        }
        return entityManager.merge(genre);
    }

    @Override
    public List<Genre> findAll() {
        return entityManager
                .createQuery("select g from Genre g", Genre.class)
                .getResultList();
    }

    @Override
    public Optional<Genre> findByName(String name) {
        List<Genre> genres = entityManager.createQuery("select g from Genre g where name = :name", Genre.class)
                .setParameter("name", name)
                .getResultList();
        if (genres.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(genres.get(0));
    }
}
