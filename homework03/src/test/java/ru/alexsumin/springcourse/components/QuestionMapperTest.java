package ru.alexsumin.springcourse.components;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.alexsumin.springcourse.domain.Question;
import ru.alexsumin.springcourse.domain.QuizQuestion;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionMapperTest {

    @DisplayName("Mapping test")
    @Test
    void toQuizQuestionTest() {
        var questionMapper = new QuestionMapper();

        var prompt = "Some text for test";
        var answer = "answer";

        List<String> variants = Arrays.asList("test", "anothertest", answer);
        var source = Question.builder()
                .prompt(prompt)
                .variants(variants)
                .answer(answer)
                .build();

        QuizQuestion mappedQuestion = questionMapper.toQuizQuestion(source);
        List<String> mappedVariants = mappedQuestion.getVariants();

        //extract correct
        int expectedCorrectNumber = IntStream.range(1, mappedVariants.size()+1)
                .filter(i -> answer.equals(mappedVariants.get(i-1).substring(3)))
                .findFirst()
                .getAsInt();

        assertEquals(prompt, mappedQuestion.getText());
        assertEquals(variants.size(), mappedVariants.size());
        assertEquals(expectedCorrectNumber, mappedQuestion.getCorrectNumber());
    }
}