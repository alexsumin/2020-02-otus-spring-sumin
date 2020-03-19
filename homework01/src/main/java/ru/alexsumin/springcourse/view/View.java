package ru.alexsumin.springcourse.view;

import ru.alexsumin.springcourse.domain.QuizQuestion;
import ru.alexsumin.springcourse.domain.Result;
import ru.alexsumin.springcourse.domain.Student;

public interface View {
    void showGreeting();
    Student getStudentData();
    void showStart();
    String askQuestion(QuizQuestion question);
    void showRightAnswer();
    void showWrongAnswer();
    void showResult(Result result);
    void showGoodbye();
}
