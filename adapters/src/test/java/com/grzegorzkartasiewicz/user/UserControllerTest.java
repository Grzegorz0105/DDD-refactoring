package com.grzegorzkartasiewicz.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserFacade userFacade;

    @Test
    @DisplayName("should show user profile when user exists")
    void shouldShowUserProfileWhenUserExists() throws Exception {
        // given
        int userId = 1;
        var user = User.restore(new UserSnapshot(userId, "Test", "User", 30));
        given(userRepository.findById(userId)).willReturn(Optional.of(user));

        // when & then
        mockMvc.perform(get("/user").param("user", String.valueOf(userId)))
                .andExpect(status().isOk())
                .andExpect(view().name("user"))
                .andExpect(model().attribute("user", user));
    }

    @Test
    @DisplayName("should show error message when user does not exist")
    void shouldShowErrorMessageWhenUserDoesNotExist() throws Exception {
        // given
        int userId = 99;
        given(userRepository.findById(anyInt())).willReturn(Optional.empty());

        // when & then
        mockMvc.perform(get("/user").param("user", String.valueOf(userId)))
                .andExpect(status().isOk())
                .andExpect(view().name("user"))
                .andExpect(model().attribute("message", "User not found"));
    }

    @Test
    @DisplayName("should create post for logged in user")
    void shouldCreatePostForLoggedInUser() throws Exception {
        // given
        var loggedUser = new UserDTO();
        loggedUser.setId(1);
        String description = "New post from a controller test";

        // when & then
        mockMvc.perform(post("/user")
                        .param("description", description)
                        .sessionAttr("user", loggedUser))
                .andExpect(status().isOk())
                .andExpect(view().name("user"));

        // verify
        verify(userFacade).createPost(eq(loggedUser.getId()), eq(description));
    }
}
