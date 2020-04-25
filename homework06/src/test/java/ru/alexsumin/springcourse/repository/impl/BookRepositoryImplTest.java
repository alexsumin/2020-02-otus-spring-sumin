package ru.alexsumin.springcourse.repository.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.alexsumin.springcourse.domain.Book;
import ru.alexsumin.springcourse.util.ProjectUtil;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DisplayName("Репозиторий для работы с книгами")
@DataJpaTest
@Import(BookRepositoryImpl.class)
class BookRepositoryImplTest {

    @Autowired
    private BookRepositoryImpl repository;

    @Autowired
    private TestEntityManager entityManager;

    @DisplayName("Сохранение книги")
    @Test
    void saveTest() {
        Book toSave = Book.builder()
                .title("some title")
                .published(ProjectUtil.toDate("2006-09-13"))
                .build();
        var saved = repository.save(toSave);
        var fromDb = entityManager.find(Book.class, saved.getId());
        assertEquals(saved, fromDb);
    }

    @DisplayName("Нахождение книги по id")
    @Test
    void findByIdTest() {
        Optional<Book> fromDb = repository.findById(3L);

        assertThat(fromDb)
                .isPresent()
                .matches(book -> book.get().getId().equals(3L))
                .matches(book -> book.get().getAuthor().getName().equals("Bruce Eckel"))
                .matches(book -> book.get().getTitle().equals("On Java8"))
                .matches(book -> book.get().getComments().size() == 1);
    }

    @DisplayName("Нахождение всех книг")
    @Test
    void findAllTest() {
        val books = repository.findAll();
        assertEquals(4, books.size());
    }

    @DisplayName("Удаление по id")
    @Test
    void deleteByIdTest() {
        repository.deleteById(2L);
        var shouldEmpty = Optional.ofNullable(entityManager.find(Book.class, 2L));
        assertFalse(shouldEmpty.isPresent());
    }
}