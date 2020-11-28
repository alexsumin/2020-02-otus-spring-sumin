package ru.alexsumin.springcourse.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class ToDoDTO {
    @JsonProperty("id")
    Long id;
    @JsonProperty("text")
    String text;
    @JsonProperty("assigner")
    String assigner;
    @JsonProperty("is_finished")
    Boolean isFinished;

    @Override
    public String toString() {
        return
                "id=" + id +
                ";text='" + text +
                ";assigner='" + assigner +
                ";isFinished=" + isFinished +
                ';';
    }
}
