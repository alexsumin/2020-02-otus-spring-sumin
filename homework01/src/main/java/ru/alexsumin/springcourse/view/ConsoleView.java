package ru.alexsumin.springcourse.view;

import ru.alexsumin.springcourse.domain.QuizQuestion;
import ru.alexsumin.springcourse.domain.Result;
import ru.alexsumin.springcourse.domain.Student;
import ru.alexsumin.springcourse.util.UIConstants;

import java.util.Optional;
import java.util.Scanner;

public class ConsoleView implements View {
    private Scanner scanner;

    public ConsoleView() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void showGreeting() {
        System.out.println(UIConstants.GREETING);
    }

    @Override
    public Student getStudentData() {
        Student student = new Student();
        System.out.println(UIConstants.FIRST_NAME_QUESTION);
        student.setFirstName(getUserInput());
        System.out.println(UIConstants.LAST_NAME_QUESTION);
        student.setLastName(getUserInput());
        return student;
    }

    @Override
    public void showStart() {
        System.out.println(UIConstants.START);
    }

    @Override
    public String askQuestion(QuizQuestion question) {
        System.out.println(prepareQuestionForPrint(question));
        return getUserInput();
    }

    @Override
    public void showRightAnswer() {
        System.out.println(UIConstants.RIGHT_ANSWER);
    }

    @Override
    public void showWrongAnswer() {
        System.out.println(UIConstants.WRONG_ANSWER);
    }


    @Override
    public void showResult(Result result) {
        System.out.printf(UIConstants.RESULT_UNFORMATTED,
                result.getStudent().getFirstName(),
                result.getStudent().getLastName(),
                result.getCorrect(),
                result.getTotal());
    }

    @Override
    public void showGoodbye() {
        System.out.println(UIConstants.GOODBYE);
        finish();
    }

    private String getUserInput() {
        return scanner.nextLine();
    }

    private void finish() {
        Optional.ofNullable(scanner).ifPresent(Scanner::close);
    }

    private String prepareQuestionForPrint(QuizQuestion question) {
        StringBuilder forPrint = new StringBuilder()
                .append(question.getText())
                .append("\n");
        question.getNumberVariantMap()
                .forEach((key, value) -> forPrint.append(key).append(". ").append(value).append("\n"));
        return forPrint.toString();
    }
}
