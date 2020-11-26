package ru.alexsumin.springcourse.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.alexsumin.springcourse.domain.Book;
import ru.alexsumin.springcourse.exception.NotFoundException;
import ru.alexsumin.springcourse.service.BookService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @Mock
    BookService bookService;

    BookController controller;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
        controller = new BookController(bookService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    public void testGetBook() throws Exception {
        var book = Book.builder().id(1L).build();

        when(bookService.findById(anyLong())).thenReturn(book);

        mockMvc.perform(get("/book/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("book/show"))
                .andExpect(model().attributeExists("book"));
    }

    @Test
    public void testGetBookNotFound() throws Exception {
        when(bookService.findById(anyLong())).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/book/1/show"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("404error"));
    }

    @Test
    public void testGetBookBadRequest() throws Exception {
        mockMvc.perform(get("/book/asdf/show"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("400error"));
    }

    @Test
    public void testGetNewBookForm() throws Exception {
        mockMvc.perform(get("/book/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("book/bookform"))
                .andExpect(model().attributeExists("book"));
    }

    @Test
    public void testPostNewBookForm() throws Exception {
        Book book = Book.builder().id(2L).build();

        when(bookService.save(any())).thenReturn(book);

        mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("title", "some string")
                .param("directions", "some directions")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/book/2/show"));
    }

    @Test
    public void testGetUpdateView() throws Exception {
        Book book = Book.builder().id(2L).build();

        when(bookService.findById(anyLong())).thenReturn(book);

        mockMvc.perform(get("/book/2/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("book/bookform"))
                .andExpect(model().attributeExists("book"));
    }

    @Test
    public void testDeleteAction() throws Exception {
        mockMvc.perform(get("/book/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(bookService, times(1)).deleteById(anyLong());
    }
}
