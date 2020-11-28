package ru.alexsumin.springcourse.handlers;

import org.springframework.core.GenericTypeResolver;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

public abstract class BaseHandler<T> {
    protected ReactiveMongoRepository<T, String> repository;
    private Class<?> clazz;

    public BaseHandler(ReactiveMongoRepository<T, String> repository) {
        this.repository = repository;
        this.clazz = Objects.requireNonNull(
                GenericTypeResolver.resolveTypeArguments(repository.getClass(), ReactiveMongoRepository.class))[0];
    }

    private Class<?> getaClass() {
        return clazz;
    }

    public Mono<ServerResponse> list() {
        return ok().contentType(APPLICATION_JSON).body(repository.findAll(), getaClass());
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<? extends T> mono = (Mono<? extends T>) request.bodyToMono(getaClass());
        return ok().contentType(APPLICATION_JSON).body(
                (Object) mono.flatMap(repository::save), getaClass());
    }

    public Mono<ServerResponse> get(ServerRequest request) {
        return ok().contentType(APPLICATION_JSON)
                .body(repository.findById(request.pathVariable("id")), getaClass())
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        repository.findById(request.pathVariable("id"))
                .flatMap(repository::delete);
        return ok().body(BodyInserters.fromValue("ok"));
    }
}
