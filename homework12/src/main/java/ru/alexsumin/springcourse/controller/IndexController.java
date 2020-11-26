package ru.alexsumin.springcourse.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.alexsumin.springcourse.service.BookService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class IndexController {
    private final BookService bookService;

    @RequestMapping({"", "/", "index"})
    public String getIndexPage(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "index";
    }
}
