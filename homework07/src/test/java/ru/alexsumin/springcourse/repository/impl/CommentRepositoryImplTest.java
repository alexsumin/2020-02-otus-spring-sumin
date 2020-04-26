package ru.alexsumin.springcourse.repository.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.alexsumin.springcourse.domain.Book;
import ru.alexsumin.springcourse.domain.Comment;
import ru.alexsumin.springcourse.repository.CommentRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Репозиторий для работы с комментариями")
@DataJpaTest
class CommentRepositoryImplTest {

    @Autowired
    private CommentRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @DisplayName("Сохранение комментария")
    @Test
    void saveTest() {
        long bookId = 4L;
        Book book = Book.builder().id(bookId).build();
        Comment toSave = Comment.builder()
                .text("some text")
                .book(book)
                .build();

        var saved = repository.save(toSave);
        var fromDb = entityManager.find(Comment.class, saved.getId());
        assertEquals(saved, fromDb);
        assertEquals(bookId, fromDb.getBook().getId());
    }

    @DisplayName("Нахождение всех комментариев")
    @Test
    void findAllTest() {
        val comments = repository.findAll();
        assertEquals(2, comments.size());
    }

    @DisplayName("Нахождение всех комментариев для книги по ее id")
    @Test
    void commentsOfBookTest() {
        val comments = repository.findAllByBookId(3L);
        assertEquals(1, comments.size());
    }
}