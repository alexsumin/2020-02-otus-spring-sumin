package ru.alexsumin.springcourse.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.alexsumin.springcourse.dto.BookDTO;
import ru.alexsumin.springcourse.dto.CommentDTO;
import ru.alexsumin.springcourse.mapper.BookMapper;
import ru.alexsumin.springcourse.mapper.CommentMapper;
import ru.alexsumin.springcourse.service.BookService;
import ru.alexsumin.springcourse.service.CommentService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/v1/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping("list")
    public List<BookDTO> list() {
        log.info("Get request");
        return bookService.findAll().stream()
                .map(bookMapper::toDTO)
                .peek(System.out::println)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void create(@RequestBody BookDTO bookDTO) {
        bookService.save(bookMapper.toBook(bookDTO));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") long id) {
        bookService.deleteById(id);
    }

    @PutMapping(value = "{id}")
    public void update(@PathVariable("id") long id, @RequestBody BookDTO bookDTO) {
        bookDTO.setId(id);
        bookService.save(bookMapper.toBook(bookDTO));
    }

    @GetMapping("{id}")
    public BookDTO show(@PathVariable("id") long id) {
        return bookMapper.toDTO(bookService.findById(id));
    }
}
