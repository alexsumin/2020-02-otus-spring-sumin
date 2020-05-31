package ru.alexsumin.springcourse.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.stream.Collectors;

@UtilityClass
public class ProjectUtil {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static <T> String joinForOutput(Collection<T> collection) {
        return collection.stream()
                .map(T::toString)
                .collect(Collectors.joining(",\n"));
    }

    public static LocalDate toDate(String source) {
        return LocalDate.parse(source, FORMAT);
    }

}
