package ru.alexsumin.springcourse.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.alexsumin.springcourse.service.BookService;
import ru.alexsumin.springcourse.service.CommentService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class CommentControllerTest {
    @Mock
    BookService bookService;
    @Mock
    CommentService commentService;

    CommentController controller;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
        controller = new CommentController(commentService, bookService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    public void testGetNewCommentForm() throws Exception {
        mockMvc.perform(get("/book/1/comment/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("book/comment/commentform"))
                .andExpect(model().attributeExists("comment"));
    }
}
