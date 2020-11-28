package ru.alexsumin.springcourse.components;

import org.springframework.stereotype.Component;
import ru.alexsumin.springcourse.domain.Question;
import ru.alexsumin.springcourse.domain.QuizQuestion;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class QuestionMapper {

    public QuizQuestion toQuizQuestion(Question question) {
        var questionVariants = question.getVariants();
        Collections.shuffle(questionVariants);
        var indexOfCorrect = findIndexOfCorrect(questionVariants, question.getAnswer());

        List<String> variants = prepareListOfVariants(questionVariants);

        return QuizQuestion.builder()
                .text(question.getPrompt())
                .variants(variants)
                .correctNumber(indexOfCorrect)
                .build();
    }

    private List<String> prepareListOfVariants(List<String> source) {
        if (source == null || source.isEmpty()) return Collections.emptyList();

        return IntStream.range(1, source.size() + 1)
                .boxed()
                .map(i1 -> i1 +". " + source.get(i1 - 1))
                .collect(Collectors.toList());
    }

    private int findIndexOfCorrect(List<String> variants, String correct) {
        return IntStream.range(1, variants.size() + 1)
                .filter(i -> correct.equals(variants.get(i - 1)))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }
}
