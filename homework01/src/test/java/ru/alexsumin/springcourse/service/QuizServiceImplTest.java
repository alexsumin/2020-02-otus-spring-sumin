package ru.alexsumin.springcourse.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.alexsumin.springcourse.domain.Question;
import ru.alexsumin.springcourse.domain.Result;
import ru.alexsumin.springcourse.domain.Student;
import ru.alexsumin.springcourse.repository.QuestionRepository;
import ru.alexsumin.springcourse.view.View;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class QuizServiceImplTest {

    @Mock
    View mockView;

    @Mock
    QuestionRepository mockRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @DisplayName("Call method run(), all answers are right")
    @Test
    void runAllCorrectTest() {
        var quizService = new QuizServiceImpl(mockRepository, mockView);

        var student = new Student("John","Doe");
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

        verify(mockView, times(1)).showGreeting();
        verify(mockView, times(1)).getStudentData();
        verify(mockView, times(1)).showStart();
        verify(mockView, times(2)).showRightAnswer();
        verify(mockView, times(0)).showWrongAnswer();
        verify(mockView, times(1)).showResult(result);
        verify(mockView, times(1)).showGoodbye();
    }

    private List<Question> getTestQuestions() {
        String variant1 = "Alright";
        var question1 = Question.builder()
                .id(1)
                .prompt("What's up?")
                .variants(Arrays.asList(variant1))
                .answer(variant1)
                .build();

        String variant2 = "C";
        var question2 = Question.builder()
                .id(2)
                .prompt("Which programming language is not object oriented programming?")
                .variants(Arrays.asList(variant2))
                .answer(variant2)
                .build();
        return Arrays.asList(question1, question2);
    }
}