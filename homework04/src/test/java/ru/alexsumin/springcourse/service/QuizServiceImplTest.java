package ru.alexsumin.springcourse.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.jline.JLineShellAutoConfiguration;
import org.springframework.shell.standard.commands.StandardCommandsAutoConfiguration;
import ru.alexsumin.springcourse.domain.QuizQuestion;
import ru.alexsumin.springcourse.domain.Result;
import ru.alexsumin.springcourse.domain.Student;
import ru.alexsumin.springcourse.repository.QuestionRepository;
import ru.alexsumin.springcourse.service.impl.QuizServiceImpl;
import ru.alexsumin.springcourse.view.View;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@EnableAutoConfiguration(exclude = {
        JLineShellAutoConfiguration.class,
        StandardCommandsAutoConfiguration.class})
class QuizServiceImplTest {

    @MockBean
    View mockView;

    @MockBean
    QuestionRepository mockRepository;

    @DisplayName("Call method run(), all answers are right")
    @Test
    void runAllCorrectTest() {
        var quizService = new QuizServiceImpl(mockRepository, mockView, 80);

        var student = new Student("John", "Doe");
        var result = Result.builder()
                .total(2)
                .student(student)
                .correct(2)
                .build();

        when(mockRepository.getQuestions()).thenReturn(getTestQuestions());

        when(mockView.getStudentData()).thenReturn(student);
        when(mockView.askQuestion(any())).thenReturn("1");

        quizService.run();

        verify(mockRepository, times(1)).getQuestions();

        InOrder inOrder = Mockito.inOrder(mockView);
        inOrder.verify(mockView, times(1)).showGreeting();
        inOrder.verify(mockView, times(1)).getStudentData();
        inOrder.verify(mockView, times(1)).showStart();
        inOrder.verify(mockView, times(1)).askQuestion(any());
        inOrder.verify(mockView, times(1)).showRightAnswer();
        inOrder.verify(mockView, times(1)).askQuestion(any());
        inOrder.verify(mockView, times(1)).showRightAnswer();
        inOrder.verify(mockView, times(1)).showResult(result);
        inOrder.verify(mockView, times(1)).showTestPassed();
        inOrder.verify(mockView, times(1)).showGoodbye();

        verify(mockView, times(1)).showGreeting();
        verify(mockView, times(1)).getStudentData();
        verify(mockView, times(1)).showStart();
        verify(mockView, times(2)).askQuestion(any());
        verify(mockView, times(2)).showRightAnswer();
        verify(mockView, never()).showWrongAnswer();
        verify(mockView, times(1)).showResult(result);
        verify(mockView, times(1)).showTestPassed();
        verify(mockView, never()).showTestNotPassed();
        verify(mockView, times(1)).showGoodbye();
    }

    private List<QuizQuestion> getTestQuestions() {
        String variant1 = "Alright";
        var question1 = QuizQuestion.builder()
                .text("What's up?")
                .variants(Arrays.asList(variant1))
                .correctNumber(1)
                .build();

        String variant2 = "C";
        var question2 = QuizQuestion.builder()
                .text("Which programming language is not object oriented programming?")
                .variants(Arrays.asList(variant2))
                .correctNumber(1)
                .build();
        return Arrays.asList(question1, question2);
    }
}