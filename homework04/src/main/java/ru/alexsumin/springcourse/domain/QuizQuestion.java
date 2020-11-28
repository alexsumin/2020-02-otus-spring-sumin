package ru.alexsumin.springcourse.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QuizQuestion {
    private String text;
    private List<String> variants;
    private int correctNumber;
}
