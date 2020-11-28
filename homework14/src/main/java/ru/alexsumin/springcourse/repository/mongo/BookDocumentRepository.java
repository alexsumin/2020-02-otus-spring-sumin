package ru.alexsumin.springcourse.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.alexsumin.springcourse.domain.document.BookDocument;

@Repository
public interface BookDocumentRepository extends MongoRepository<BookDocument, String> {
}
