package ru.alexsumin.springcourse.domain;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Result {
    private int total;
    private int correct;
    private Student student;
    public void incrementCorrect() {
        correct++;
    }
}
