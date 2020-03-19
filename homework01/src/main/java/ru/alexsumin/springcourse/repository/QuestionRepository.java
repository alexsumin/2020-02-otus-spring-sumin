package ru.alexsumin.springcourse.repository;

import ru.alexsumin.springcourse.domain.Question;

import java.util.List;

public interface QuestionRepository {
    List<Question> getQuestions();
}
