package ru.alexsumin.springcourse.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.alexsumin.springcourse.domain.document.AuthorDocument;

@Repository
public interface AuthorDocumentRepository extends MongoRepository<AuthorDocument, String> {
    AuthorDocument findByName(String name);
}
