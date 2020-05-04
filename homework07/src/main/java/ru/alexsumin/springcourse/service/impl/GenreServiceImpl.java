package ru.alexsumin.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alexsumin.springcourse.domain.Genre;
import ru.alexsumin.springcourse.repository.GenreRepository;
import ru.alexsumin.springcourse.service.GenreService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GenreServiceImpl implements GenreService {
    GenreRepository repository;

    @Transactional
    @Override
    public Genre save(Genre genre) {
        return repository.save(genre);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Genre> findByName(String name) {
        return repository.findByName(name);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Genre> findAll() {
        return repository.findAll();
    }
}
