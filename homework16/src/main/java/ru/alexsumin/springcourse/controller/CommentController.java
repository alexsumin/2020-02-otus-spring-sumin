package ru.alexsumin.springcourse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.alexsumin.springcourse.dto.CommentDTO;
import ru.alexsumin.springcourse.mapper.CommentMapper;
import ru.alexsumin.springcourse.service.CommentService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper commentMapper;

    @GetMapping("book/{id}/comment")
    public List<CommentDTO> list(@PathVariable("id") long id) {
        return commentService.commentsOfBook(id).stream()
                .map(commentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("book/{id}/comment")
    public void create(@PathVariable("id") long bookId, @RequestBody CommentDTO commentDTO) {
        commentService.create(bookId, commentMapper.toComment(commentDTO));
    }

    @PutMapping("comment/{id}")
    public void update(@PathVariable("{id}") long id, @RequestBody CommentDTO commentDTO) {
        commentDTO.setId(id);
        commentService.save(commentMapper.toComment(commentDTO));
    }
}
