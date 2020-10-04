package ru.alexsumin.springcourse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.alexsumin.springcourse.domain.Author;
import ru.alexsumin.springcourse.domain.Genre;
import ru.alexsumin.springcourse.handlers.*;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class Router {

    @Bean
    public RouterFunction<ServerResponse> libraryRoutes(AuthorHandler authorHandler,
                                                        GenreHandler genreHandler,
                                                        BookHandler bookHandler) {
        return route()
                .GET("/api/author", accept(APPLICATION_JSON), request -> authorHandler.list())
                .GET("/api/author/{id}", accept(APPLICATION_JSON), authorHandler::get)
                .POST("/api/author", contentType(APPLICATION_JSON), authorHandler::save)
                .DELETE("/api/author/{id}", accept(APPLICATION_JSON), authorHandler::delete)

                .GET("/api/genre", accept(APPLICATION_JSON), request -> genreHandler.list())
                .GET("/api/genre/{id}", accept(APPLICATION_JSON), genreHandler::get)
                .POST("/api/genre", contentType(APPLICATION_JSON), genreHandler::save)

                .GET("/api/book", accept(APPLICATION_JSON), request -> bookHandler.list())
                .GET("/api/book/{id}", accept(APPLICATION_JSON), bookHandler::get)
                .POST("/api/book", contentType(APPLICATION_JSON), bookHandler::save)
                .DELETE("/api/book/{id}", accept(APPLICATION_JSON), bookHandler::delete)
                .GET("/api/book/{id}/comment", accept(APPLICATION_JSON), bookHandler::comments)

                .build();
    }
}
