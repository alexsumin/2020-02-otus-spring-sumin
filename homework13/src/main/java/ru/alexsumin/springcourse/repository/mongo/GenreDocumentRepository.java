package ru.alexsumin.springcourse.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.alexsumin.springcourse.domain.document.GenreDocument;

@Repository
public interface GenreDocumentRepository extends MongoRepository<GenreDocument, String> {
    GenreDocument findByName(String name);
}
