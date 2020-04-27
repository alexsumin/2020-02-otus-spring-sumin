package ru.alexsumin.springcourse.util;

import lombok.experimental.UtilityClass;

import java.util.Optional;

@UtilityClass
public class InputHelper {

    public static Optional<Integer> tryParseInt(String input) {
        try {
            return Optional.of(Integer.parseInt(input));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
