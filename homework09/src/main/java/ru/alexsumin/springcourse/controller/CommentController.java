package ru.alexsumin.springcourse.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.alexsumin.springcourse.domain.Comment;
import ru.alexsumin.springcourse.service.BookService;
import ru.alexsumin.springcourse.service.CommentService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final BookService bookService;

    @GetMapping
    @RequestMapping("book/{bookId}/comment/new")
    public String newComment(@PathVariable String bookId, Model model) {
        var book = bookService.findById(Long.valueOf(bookId));

        Comment comment = Comment.builder()
                .book(book)
                .build();
        model.addAttribute("comment", comment);

        return "book/comment/commentform";
    }

    @PostMapping("book/{bookId}/comment")
    public String saveOrUpdate(@ModelAttribute @PathVariable String bookId, Comment comment) {
        var book = bookService.findById(Long.valueOf(bookId));
        comment.setBook(book);

        Comment saved = commentService.save(comment);

        return "redirect:/book/" + comment.getBook().getId() + "/show";
    }
}
