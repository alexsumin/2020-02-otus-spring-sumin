package ru.alexsumin.springcourse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.alexsumin.springcourse.dto.GenreDTO;
import ru.alexsumin.springcourse.mapper.GenreMapper;
import ru.alexsumin.springcourse.service.GenreService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/genre")
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;
    private final GenreMapper genreMapper;

    @GetMapping("/list")
    public List<GenreDTO> list() {
        return genreService.findAll().stream()
                .map(genreMapper::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void create(@RequestBody GenreDTO genreDTO) {
        genreService.save(genreMapper.toGenre(genreDTO));
    }

    @PutMapping("{id}")
    public void update(@PathVariable("{id}") long id, @RequestBody GenreDTO genreDTO) {
        genreDTO.setId(id);
        genreService.save(genreMapper.toGenre(genreDTO));
    }
}
