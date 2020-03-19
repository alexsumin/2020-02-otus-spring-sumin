package ru.alexsumin.springcourse.util;

import lombok.experimental.UtilityClass;
import ru.alexsumin.springcourse.domain.Question;
import ru.alexsumin.springcourse.domain.QuizQuestion;

import java.util.*;
import java.util.stream.IntStream;

@UtilityClass
public class QuestionMapper {

    public static QuizQuestion toQuizQuestion(Question question) {
        return QuizQuestion.builder()
                .text(question.getPrompt())
                .numberVariantMap(prepareNumberVariantMap(question.getVariants()))
                .build();
    }

    private static Map<Integer, String> prepareNumberVariantMap(List<String> variants) {
        Collections.shuffle(variants);
        SortedMap<Integer, String> map = new TreeMap<>();
        IntStream
                .range(1, variants.size() + 1)
                .boxed()
                .forEach(s -> map.put(s, variants.get(s - 1)));
        return map;
    }
}
