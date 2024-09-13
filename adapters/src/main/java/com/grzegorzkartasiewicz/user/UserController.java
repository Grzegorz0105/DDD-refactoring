package com.grzegorzkartasiewicz.user;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    public static final String MODEL_ATTRIBUTE_USER = "user";
    private final UserRepository repository;
    private final UserFacade service;

    UserController(UserRepository repository, UserFacade service) {
        this.repository = repository;
        this.service = service;
    }


    @GetMapping
    String showUser(Model model, @RequestParam int user){
        logger.info("Showing chosen user!");
        var userToShow = repository.findById(user);
        if(userToShow.isPresent()){
            model.addAttribute(MODEL_ATTRIBUTE_USER,userToShow.get());
        } else{
            model.addAttribute("message","User not found");
        }
        return MODEL_ATTRIBUTE_USER;

    }
    @PostMapping
    String addUserPost(HttpSession session, Model model,
                        String description){
        logger.info("Creating new post!");
        var loggedUser = (User) session.getAttribute(MODEL_ATTRIBUTE_USER);
        service.createPost(loggedUser.getId(),description);
        model.addAttribute(MODEL_ATTRIBUTE_USER, loggedUser);
        return MODEL_ATTRIBUTE_USER;
    }
    @PostMapping("/comments/{postId}")
    String addUserComment(HttpSession session,
            @ModelAttribute("users") UserDTO current, Model model,
                          @PathVariable int postId,
                          String description){
        logger.info("Creating new comment!");
        var loggedUser= (User) session.getAttribute(MODEL_ATTRIBUTE_USER);
        service.createComment(loggedUser,postId,description);
        model.addAttribute(MODEL_ATTRIBUTE_USER, current);
        return MODEL_ATTRIBUTE_USER;
    }

    @PostMapping("/posts/delete/{postId}")
    String deleteUserPost(@ModelAttribute("users") UserDTO current, Model model,
                          @PathVariable int postId){
        logger.info("Deleting post!");
        service.deletePost(postId);
        model.addAttribute(MODEL_ATTRIBUTE_USER, current);
        return MODEL_ATTRIBUTE_USER;
    }

    @PostMapping("/comment/delete/{commentId}")
    String deleteUserComment(@ModelAttribute("users") UserDTO current, Model model,
                          @PathVariable int commentId){
        logger.info("Deleting comment!");
        service.deleteComment(commentId);
        model.addAttribute(MODEL_ATTRIBUTE_USER, current);
        return MODEL_ATTRIBUTE_USER;
    }
    @GetMapping("/home")
    String showUserProfile(@ModelAttribute("users") UserDTO current,Model model){
        logger.info("Showing profile of logged user!");
        model.addAttribute(MODEL_ATTRIBUTE_USER,current);
        return MODEL_ATTRIBUTE_USER;

    }

    @ModelAttribute("users")
    List<UserDTO> getUsers(){
        return repository.findAll().stream().map(UserDTO::toDTO).toList();
    }
}
