package ru.alexsumin.springcourse.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.alexsumin.springcourse.dao.AuthorDao;
import ru.alexsumin.springcourse.domain.Author;
import ru.alexsumin.springcourse.service.AuthorService;

import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class AuthorServiceImpl implements AuthorService {
    AuthorDao authorDao;

    @Override
    public Author save(Author author) {
        return authorDao.save(author);
    }

    @Override
    public Optional<Author> findByName(String name) {
        return authorDao.findByName(name);
    }

    @Override
    public List<Author> findAll() {
        return authorDao.findAll();
    }
}
