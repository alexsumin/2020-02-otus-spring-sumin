package ru.alexsumin.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
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

    @Override
    public Genre save(Genre genre) {
        return repository.save(genre);
    }

    @Override
    public Optional<Genre> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Genre> findAll() {
        return repository.findAll();
    }
}
