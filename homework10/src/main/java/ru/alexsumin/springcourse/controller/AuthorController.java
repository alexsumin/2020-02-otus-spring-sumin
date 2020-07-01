package ru.alexsumin.springcourse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.alexsumin.springcourse.dto.AuthorDTO;
import ru.alexsumin.springcourse.mapper.AuthorMapper;
import ru.alexsumin.springcourse.service.AuthorService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/author")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    @GetMapping("/list")
    public List<AuthorDTO> list() {
        return authorService.findAll().stream()
                .map(authorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void create(@RequestBody AuthorDTO authorDTO) {
        authorService.save(authorMapper.toAuthor(authorDTO));
    }

    @PutMapping("{id}")
    public void update(@PathVariable("{id}") long id, @RequestBody AuthorDTO authorDTO) {
        authorDTO.setId(id);
        authorService.save(authorMapper.toAuthor(authorDTO));
    }
}
