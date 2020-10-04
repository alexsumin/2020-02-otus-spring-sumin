package ru.alexsumin.springcourse.handlers;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Component;
import ru.alexsumin.springcourse.domain.Author;

@Component
public class AuthorHandler extends BaseHandler<Author> {
    public AuthorHandler(ReactiveMongoRepository<Author, String> repository) {
        super(repository);
    }
}
