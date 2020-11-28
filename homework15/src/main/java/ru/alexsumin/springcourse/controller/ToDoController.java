package ru.alexsumin.springcourse.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alexsumin.springcourse.dto.ToDoDTO;
import ru.alexsumin.springcourse.mapper.ToDoMapper;
import ru.alexsumin.springcourse.service.ToDoService;

@Slf4j
@RestController
@RequestMapping("/v1/")
@RequiredArgsConstructor
public class ToDoController {
    private final ToDoService service;
    private final ToDoMapper mapper;

    @PostMapping("todo")
    public ToDoDTO create(@RequestBody ToDoDTO dto) {
        return mapper.toDTO(service.create(dto));
    }

}
