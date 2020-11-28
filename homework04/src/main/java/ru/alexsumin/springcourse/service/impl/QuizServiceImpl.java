package ru.alexsumin.springcourse.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.alexsumin.springcourse.domain.QuizQuestion;
import ru.alexsumin.springcourse.domain.Result;
import ru.alexsumin.springcourse.domain.Student;
import ru.alexsumin.springcourse.repository.QuestionRepository;
import ru.alexsumin.springcourse.service.QuizService;
import ru.alexsumin.springcourse.util.InputHelper;
import ru.alexsumin.springcourse.view.View;

import java.util.List;
import java.util.function.Consumer;

@Service
public class QuizServiceImpl implements QuizService {
    private final QuestionRepository repository;
    private final View view;
    private final Integer percentage;

    public QuizServiceImpl(QuestionRepository repository,
                           View view,
                           @Value("${quiz.success.percentage}")Integer percentage) {
        this.repository = repository;
        this.view = view;
        this.percentage = percentage;
    }

    public void run() {
        view.showGreeting();
        Student student = view.getStudentData();
        view.showStart();

        List<QuizQuestion> questions = repository.getQuestions();
        Result result = Result.builder()
                .student(student)
                .total(questions.size())
                .build();

        questions.forEach(showVariantAndCountCorrect(result));

        view.showResult(result);

        if (isSuccessful(result)) {
            view.showTestPassed();
        } else{
            view.showTestNotPassed();
        }
        view.showGoodbye();
    }

    private boolean isSuccessful(Result result) {
        return ((result.getCorrect()*100/result.getTotal()) >= percentage);
    }

    private Consumer<QuizQuestion> showVariantAndCountCorrect(Result result) {
        return question -> InputHelper.tryParseInt(view.askQuestion(question))
                .ifPresent(i -> {
                    if (i == question.getCorrectNumber()) {
                        result.incrementCorrect();
                        view.showRightAnswer();
                    } else {
                        view.showWrongAnswer();
                    }
                });
    }
}
