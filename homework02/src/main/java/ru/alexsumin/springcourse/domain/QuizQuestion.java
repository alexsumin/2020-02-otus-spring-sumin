package ru.alexsumin.springcourse.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class QuizQuestion {
    private String text;
    private List<String> variants;
    private int correctNumber;
}
