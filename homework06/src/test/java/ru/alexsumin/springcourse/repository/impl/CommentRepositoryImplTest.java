package ru.alexsumin.springcourse.repository.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.alexsumin.springcourse.domain.Comment;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Репозиторий для работы с комментариями")
@DataJpaTest
@Import(CommentRepositoryImpl.class)
class CommentRepositoryImplTest {

    @Autowired
    private CommentRepositoryImpl repository;

    @Autowired
    private TestEntityManager entityManager;

    @DisplayName("Сохранение комментария")
    @Test
    void saveTest() {
        Comment toSave = Comment.builder()
                .text("some text")
                .bookId(4L)
                .build();
        var saved = repository.save(toSave);
        var fromDb = entityManager.find(Comment.class, saved.getId());
        assertEquals(saved, fromDb);
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
        val comments = repository.commentsOfBook(3L);
        assertEquals(1, comments.size());
    }
}