package ru.alexsumin.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.alexsumin.springcourse.service.QuizService;
import ru.alexsumin.springcourse.service.ShellService;

@ShellComponent
@RequiredArgsConstructor
public class ShellServiceImpl implements ShellService {
    private final QuizService quizService;

    @Override
    @ShellMethod(key = "start", value = "Start testing")
    public void run() {
        quizService.run();
    }
}
