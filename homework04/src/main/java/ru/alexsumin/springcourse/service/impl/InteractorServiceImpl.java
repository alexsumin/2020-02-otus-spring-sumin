package ru.alexsumin.springcourse.service.impl;

import ru.alexsumin.springcourse.service.InteractorService;

import javax.annotation.PreDestroy;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Optional;
import java.util.Scanner;

public class InteractorServiceImpl implements InteractorService {
    private final Scanner scanner;
    private final PrintStream printStream;

    public InteractorServiceImpl(InputStream inputStream, PrintStream outputStream) {
        this.scanner = new Scanner(inputStream);
        this.printStream = outputStream;
    }

    @Override
    public String getUserInput() {
        return scanner.nextLine();
    }

    @Override
    public void show(String string) {
        printStream.println(string);
    }

    @PreDestroy
    public void finish() {
        Optional.ofNullable(scanner).ifPresent(Scanner::close);
    }
}
