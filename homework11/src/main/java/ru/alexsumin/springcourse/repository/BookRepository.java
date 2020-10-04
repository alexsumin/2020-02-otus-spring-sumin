package ru.alexsumin.springcourse.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import ru.alexsumin.springcourse.domain.Book;

@Repository
public interface BookRepository extends ReactiveMongoRepository<Book, String> {
}
