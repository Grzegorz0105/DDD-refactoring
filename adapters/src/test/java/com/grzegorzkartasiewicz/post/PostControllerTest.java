package com.grzegorzkartasiewicz.post;

import com.grzegorzkartasiewicz.comment.CommentDTO;
import com.grzegorzkartasiewicz.post.vo.PostId;
import com.grzegorzkartasiewicz.user.UserDTO;
import com.grzegorzkartasiewicz.user.UserFacade;
import com.grzegorzkartasiewicz.user.vo.UserId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostRepository postRepository;

    @MockBean
    private PostFacade postFacade;

    @MockBean
    private UserFacade userFacade;

    @Test
    @DisplayName("should return posts and users for a given search query")
    void shouldReturnPostsAndUsersForSearchQuery() throws Exception {
        // given
        String query = "test";
        var postDto = new PostDTO(1, "A test post", new UserId(1), "name", "surname", List.of(new CommentDTO(1,
                "decription", new PostId(1), new UserId(1), "name", "surname")));
        var userDto = new UserDTO();
        userDto.setId(2);
        userDto.setName("Test User");

        given(postFacade.searchPosts(query)).willReturn(List.of(postDto));
        given(userFacade.searchUsers(query)).willReturn(List.of(userDto));

        // when & then
        mockMvc.perform(get("/posts/search").param("search", query))
                .andExpect(status().isOk())
                .andExpect(view().name("posts"))
                .andExpect(model().attribute("posts", List.of(postDto)))
                .andExpect(model().attribute("users", List.of(userDto)));
    }

    @Test
    @DisplayName("should return home page with all posts")
    void shouldReturnHomePageWithAllPosts() throws Exception {
        // given
        var postDto = new PostDTO(1, "A test post", new UserId(1), "name", "surname", List.of(new CommentDTO(1,
                "decription", new PostId(1), new UserId(1), "name", "surname")));
        given(postRepository.findAll()).willReturn(Collections.singletonList(Post.restore(new PostSnapshot(1, "A sample post", new UserId(1)))));


        // when & then
        mockMvc.perform(get("/posts/home"))
                .andExpect(status().isOk())
                .andExpect(view().name("posts"))
                .andExpect(model().attributeExists("posts"))
                .andExpect(model().attributeExists("post"));
    }
}
