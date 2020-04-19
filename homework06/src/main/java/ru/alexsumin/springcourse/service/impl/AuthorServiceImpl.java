package ru.alexsumin.springcourse.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alexsumin.springcourse.domain.Author;
import ru.alexsumin.springcourse.repository.AuthorRepository;
import ru.alexsumin.springcourse.service.AuthorService;

import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class AuthorServiceImpl implements AuthorService {
    AuthorRepository repository;

    @Transactional
    @Override
    public Author save(Author author) {
        return repository.save(author);
    }

    @Override
    public Optional<Author> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Author> findAll() {
        return repository.findAll();
    }
}
