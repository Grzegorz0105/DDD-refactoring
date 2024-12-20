package com.grzegorzkartasiewicz.post;

import com.grzegorzkartasiewicz.comment.vo.CommentId;
import com.grzegorzkartasiewicz.user.UserDTO;
import com.grzegorzkartasiewicz.user.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/posts")
class PostController {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);
    public static final String MODEL_ATTRIBUTE_POSTS = "posts";
    public static final String MODEL_ATTRIBUTE_POST = "post";
    private final PostRepository repository;
    private final PostFacade service;
    private final UserFacade userFacade;

    PostController(PostRepository repository, PostFacade service, UserFacade userFacade) {
        this.repository = repository;
        this.service = service;
        this.userFacade = userFacade;
    }

    @GetMapping("/home")
    String showHomePage(Model model){
        logger.info("Showing home page!");
        var postToEdit = new PostDTO();
        model.addAttribute(MODEL_ATTRIBUTE_POSTS, getPosts());
        model.addAttribute("post", postToEdit);
        return MODEL_ATTRIBUTE_POSTS;
    }
    @GetMapping
    String showPosts(HttpSession session,Model model){
        logger.info("Showing home page for logged user!");
        var postToEdit = new PostDTO();
        session.setAttribute(MODEL_ATTRIBUTE_POSTS, getPosts());
        model.addAttribute(MODEL_ATTRIBUTE_POST, postToEdit);
        return MODEL_ATTRIBUTE_POSTS;

    }
    @PostMapping("/{id}")
    String addUserPost(Model model,
                       @PathVariable int id,
                       String description){
        logger.info("Creating new post!");
        userFacade.createPost(id,description);
        model.addAttribute(MODEL_ATTRIBUTE_POSTS, getPosts());
        return MODEL_ATTRIBUTE_POSTS;
    }
    @PostMapping("/comments/{postId}")
    String addUserPostComment(
                        HttpSession session,
                       Model model,
                       @PathVariable int postId,
                       String description){
        logger.info("Creating new comment!");
        UserDTO user = (UserDTO) session.getAttribute("user");
        service.createComment(user, postId, description);
        model.addAttribute(MODEL_ATTRIBUTE_POSTS, getPosts());
        return MODEL_ATTRIBUTE_POSTS;
    }
    @GetMapping("/search")
    String searchUsersAndPosts(Model model, @RequestParam String search){
        logger.info("Showing searched users and posts!");
        model.addAttribute(MODEL_ATTRIBUTE_POSTS, service.searchPosts(search));
        model.addAttribute("users", userFacade.searchUsers(search));
        return MODEL_ATTRIBUTE_POSTS;
    }
    @PostMapping("/comment/delete/{commentId}")
    String deleteComment(HttpSession session,
                         Model model,
                         @PathVariable int commentId){
        logger.info("Deleting comment!");
        service.deleteComment(new CommentId(commentId));
        model.addAttribute(MODEL_ATTRIBUTE_POSTS, getPosts());
        return MODEL_ATTRIBUTE_POSTS;
    }
    @PostMapping("/delete/{postId}")
    String deletePost(HttpSession session,
                         Model model,
                         @PathVariable int postId){
        logger.info("Deleting post!");
        service.deletePost(postId);
        model.addAttribute(MODEL_ATTRIBUTE_POSTS, getPosts());
        return MODEL_ATTRIBUTE_POSTS;
    }

    @ModelAttribute("posts")
    List<PostDTO> getPosts(){
        return repository.findAll().stream().map(PostDTO::toDTO).toList();
    }
}
