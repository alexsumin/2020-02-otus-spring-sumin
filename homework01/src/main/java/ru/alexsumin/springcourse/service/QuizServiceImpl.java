package ru.alexsumin.springcourse.service;

import ru.alexsumin.springcourse.domain.Question;
import ru.alexsumin.springcourse.domain.QuizQuestion;
import ru.alexsumin.springcourse.domain.Result;
import ru.alexsumin.springcourse.domain.Student;
import ru.alexsumin.springcourse.repository.QuestionRepository;
import ru.alexsumin.springcourse.util.QuestionMapper;
import ru.alexsumin.springcourse.util.UIConstants;
import ru.alexsumin.springcourse.view.View;

import java.util.*;
import java.util.stream.IntStream;

public class QuizServiceImpl implements QuizService {
    private final QuestionRepository repository;
    private final View view;

    public QuizServiceImpl(QuestionRepository repository, View view) {
        this.repository = repository;
        this.view = view;
    }

    public void run() {
        view.showGreeting();
        Student student = view.getStudentData();
        view.showStart();

        List<Question> questions = repository.getQuestions();
        Result result = Result.builder()
                .student(student)
                .total(questions.size())
                .build();

        questions.forEach(question -> {
            if (ask(question)) {
                view.showRightAnswer();
                result.incrementCorrect();
            } else {
                view.showWrongAnswer();
            }
        });

        view.showResult(result);
        view.showGoodbye();
    }

    private boolean ask(Question question) {
        var quizQuestion = QuestionMapper.toQuizQuestion(question);
        var studentAnswer = view.askQuestion(quizQuestion);
        Optional<Integer> optionalInteger = tryParseInt(studentAnswer);
        if (optionalInteger.isPresent()) {
            String value = quizQuestion.getNumberVariantMap().get(optionalInteger.get());
            return question.getAnswer().equalsIgnoreCase(value);
        }
        return false;
    }

    private Optional<Integer> tryParseInt(String input) {
        try {
            return Optional.of(Integer.parseInt(input));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
