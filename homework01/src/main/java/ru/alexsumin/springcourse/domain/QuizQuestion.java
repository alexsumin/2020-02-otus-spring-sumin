package ru.alexsumin.springcourse.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class QuizQuestion {
    String text;
    Map<Integer, String> numberVariantMap;
}
