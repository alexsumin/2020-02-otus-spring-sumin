package ru.alexsumin.springcourse.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import ru.alexsumin.springcourse.domain.User;
import ru.alexsumin.springcourse.service.UserService;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("Signup controller")
@WebMvcTest
@WithMockUser
@ContextConfiguration(classes = SignupController.class)
class SignupControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @SneakyThrows
    @Test
    void shouldShowSignupForm() {
        this.mvc.perform(get("/signup"))
                .andExpect(view().name("signup"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("user", new User()));

    }

    @SneakyThrows
    @Test
    void shouldCreateNewUserThenShowLoginForm() {
        var user = User.builder()
                .id(42L)
                .email("test@test.com")
                .username("admin")
                .build();


        when(userService.save(any())).thenReturn(user);

        this.mvc.perform(post("/signup")
                .param("user", user.toString())
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(userService, times(1)).save(new User());

    }
}