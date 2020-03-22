package ru.alexsumin.springcourse.domain;

import com.opencsv.bean.CsvBindAndSplitByPosition;
import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @CsvBindByPosition(position = 0)
    private Integer id;
    @CsvBindByPosition(position = 1)
    private String prompt;
    @CsvBindAndSplitByPosition(position = 2, elementType = String.class, splitOn = "\\|", collectionType = List.class)
    private List<String> variants;
    @CsvBindByPosition(position = 3)
    private String answer;
}
