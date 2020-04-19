package ru.alexsumin.springcourse.util;

import lombok.experimental.UtilityClass;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.stream.Collectors;

@UtilityClass
public class ProjectUtil {
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static <T> String joinForOutput(Collection<T> collection) {
        return collection.stream()
                .map(T::toString)
                .collect(Collectors.joining(",\n"));
    }

    public static Date toDate(String source) {
        try {
            return new Date(FORMAT.parse(source).getTime());
        } catch (ParseException e) {
            throw new RuntimeException("Wrong date format");
        }
    }

}
