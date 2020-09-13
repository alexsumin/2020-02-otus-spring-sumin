package ru.alexsumin.springcourse.view;

import org.springframework.stereotype.Component;
import ru.alexsumin.springcourse.components.LocalizationProvider;
import ru.alexsumin.springcourse.domain.QuizQuestion;
import ru.alexsumin.springcourse.domain.Result;
import ru.alexsumin.springcourse.domain.Student;
import ru.alexsumin.springcourse.service.InteractorService;

@Component
public class ConsoleView implements View {
    private final InteractorService interactor;
    private final LocalizationProvider provider;

    public ConsoleView(InteractorService interactor, LocalizationProvider provider) {
        this.interactor = interactor;
        this.provider = provider;
    }

    @Override
    public void showGreeting() {
        interactor.show(provider.getLocalized("ui.greeting"));
    }

    @Override
    public Student getStudentData() {
        var student = new Student();
        interactor.show(provider.getLocalized("ui.question.firstname"));
        student.setFirstName(interactor.getUserInput());
        interactor.show(provider.getLocalized("ui.question.lastname"));
        student.setLastName(interactor.getUserInput());
        return student;
    }

    @Override
    public void showStart() {
        interactor.show(provider.getLocalized("ui.start"));
    }

    @Override
    public String askQuestion(QuizQuestion question) {
        interactor.show(prepareQuestionForPrint(question));
        return interactor.getUserInput();
    }

    @Override
    public void showRightAnswer() {
        interactor.show(provider.getLocalized("ui.answer.right"));
    }

    @Override
    public void showWrongAnswer() {
        interactor.show(provider.getLocalized("ui.answer.wrong"));
    }

    @Override
    public void showResult(Result result) {
        interactor.show(String.format(provider.getLocalized("ui.result"),
                result.getStudent().getFirstName(),
                result.getStudent().getLastName(),
                result.getCorrect(),
                result.getTotal()));
    }

    @Override
    public void showTestPassed() {
        interactor.show(provider.getLocalized("ui.test.passed"));
    }

    @Override
    public void showTestNotPassed() {
        interactor.show(provider.getLocalized("ui.test.notpassed"));
    }

    @Override
    public void showGoodbye() {
        interactor.show(provider.getLocalized("ui.goodbye"));
    }

    private String prepareQuestionForPrint(QuizQuestion question) {
        return question.getText()
                .concat("\n")
                .concat(String.join("\n", question.getVariants()));
    }
}
