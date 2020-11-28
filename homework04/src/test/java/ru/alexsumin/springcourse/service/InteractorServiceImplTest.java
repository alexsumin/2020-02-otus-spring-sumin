package ru.alexsumin.springcourse.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.alexsumin.springcourse.service.impl.InteractorServiceImpl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class InteractorServiceImplTest {

    InteractorService interactorService;
    @Mock
    InputStream mockInput;
    @Mock
    PrintStream mockOutput;

    @BeforeEach
    void setUp() {
        interactorService = new InteractorServiceImpl(mockInput, mockOutput);
    }

    @DisplayName("Call show() test")
    @Test
    public void showMethodTest() {
        var justTestText = "Some text";
        interactorService.show(justTestText);

        verify(mockOutput, only()).println(justTestText);
    }

    @DisplayName("Call getUserInput() test, I guess useless test")
    @Test
    public void getUserInputTest() throws IOException {
        var input = "mock user input";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        var interactorService = new InteractorServiceImpl(in, mockOutput);

        assertEquals(input, interactorService.getUserInput());
        in.close();
    }
}