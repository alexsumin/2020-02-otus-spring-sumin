package ru.alexsumin.springcourse.repository;

import ru.alexsumin.springcourse.domain.QuizQuestion;

import java.util.List;

public interface QuestionRepository {
    List<QuizQuestion> getQuestions();
}
