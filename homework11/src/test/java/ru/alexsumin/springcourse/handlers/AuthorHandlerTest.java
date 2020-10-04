package ru.alexsumin.springcourse.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.alexsumin.springcourse.domain.Author;
import ru.alexsumin.springcourse.repository.AuthorRepository;

import static org.mockito.Mockito.*;

@SpringBootTest
public class AuthorHandlerTest {

    @Autowired
    private RouterFunction routerFunction;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthorRepository authorRepository;

    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToRouterFunction(routerFunction).build();
    }

    @Test
    void shouldReturnAuthors() throws JsonProcessingException {
        var authorName = "Lev Nikolayevich Tolstoy";
        var author =  Author.builder()
                .name(authorName)
                .build();
        when(authorRepository.findAll()).thenReturn(Flux.just(author));

        webTestClient.get()
                .uri("/api/author")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0]", objectMapper.writeValueAsString(author));

        verify(authorRepository, times(1)).findAll();
    }

    @Test
    void shouldSaveAuthor() throws JsonProcessingException {
        var authorName = "Lev Nikolayevich Tolstoy";
        var author =  Author.builder()
                .name(authorName)
                .build();
        when(authorRepository.save(author)).thenReturn(Mono.just(author));

        webTestClient.post()
                .uri("/api/author")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(author)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .json(objectMapper.writeValueAsString(author));

        verify(authorRepository, times(1)).save(author);
    }
}
