package ru.alexsumin.springcourse.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import ru.alexsumin.springcourse.domain.Author;
import ru.alexsumin.springcourse.domain.Book;
import ru.alexsumin.springcourse.domain.Genre;
import ru.alexsumin.springcourse.service.BookService;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
public class IndexControllerTest {

    @Mock
    BookService bookService;

    @Mock
    Model model;

    IndexController controller;


    @BeforeEach
    public void setUp() throws Exception {
        controller = new IndexController(bookService);
    }

    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void getIndexPage() {
        var authorName = "Lev Nikolayevich Tolstoy";
        var author =  Author.builder()
                .name(authorName)
                .build();
        var novel = "Novel";
        var genre = Genre.builder()
                .name(novel)
                .build();
        var testBook = Book.builder()
                .title("Some title")
                .published(new Date(System.currentTimeMillis()))
                .genre(genre)
                .author(author)
                .build();


        when(bookService.findAll()).thenReturn(List.of(testBook, new Book()));

        //when
        String viewPage = controller.getIndexPage(model);
        ArgumentCaptor<List<Book>> argumentCaptor = ArgumentCaptor.forClass(List.class);

        //then
        assertEquals("index", viewPage);
        verify(bookService, times(1)).findAll();
        verify(model, times(1)).addAttribute(eq("books"), argumentCaptor.capture());
        List<Book> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());
    }


}
