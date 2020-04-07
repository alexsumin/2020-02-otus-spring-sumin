package ru.alexsumin.springcourse.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(exclude = "id")
public class Genre {
    Long id;
    String name;
}
