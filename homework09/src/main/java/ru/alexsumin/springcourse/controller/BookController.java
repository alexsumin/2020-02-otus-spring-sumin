package ru.alexsumin.springcourse.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.alexsumin.springcourse.domain.Book;
import ru.alexsumin.springcourse.exception.NotFoundException;
import ru.alexsumin.springcourse.service.BookService;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private static final String BOOKFORM_URL = "book/bookform";

    @GetMapping("/book/{id}/show")
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("book", bookService.findById(Long.valueOf(id)));
        return "book/show";
    }

    @GetMapping("book/new")
    public String newBook(Model model){
        model.addAttribute("book", new Book());

        return BOOKFORM_URL;
    }

    @GetMapping("book/{id}/update")
    public String updateBook(@PathVariable String id, Model model){
        model.addAttribute("book", bookService.findById(Long.valueOf(id)));
        return BOOKFORM_URL;
    }

    @PostMapping("book")
    public String saveOrUpdate(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return BOOKFORM_URL;
        }

        Book savedBook = bookService.save(book);
        return "redirect:/book/" + savedBook.getId() + "/show";
    }

    @GetMapping("book/{id}/delete")
    public String deleteById(@PathVariable String id){
        log.debug("Deleting id: " + id);

        bookService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception){
        log.error("Handling not found exception");
        log.error(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("404error");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }
}
