package ru.alexsumin.springcourse.handlers;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Component;
import ru.alexsumin.springcourse.domain.Genre;

@Component
public class GenreHandler extends BaseHandler<Genre> {
    public GenreHandler(ReactiveMongoRepository<Genre, String> repository) {
        super(repository);
    }
}
