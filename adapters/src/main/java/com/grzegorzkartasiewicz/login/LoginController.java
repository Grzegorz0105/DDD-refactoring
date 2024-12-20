package com.grzegorzkartasiewicz.login;
/*
 * Spring controller made for logging and signing up users.
 *
 * @author Grzegorz Kartasiewicz
 *
 */

import com.grzegorzkartasiewicz.user.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/")
class LoginController implements ErrorController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    public static final String MODEL_ATTRIBUTE_LOGIN = "login";
    public static final String SESSION_ATTRIBUTE_USER = "user";
    private final LoginRepository repository;
    private final LoginFacade service;

    LoginController(LoginRepository repository, LoginFacade service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping
    String login(Model model){
        logger.info("Showing login template!");
        model.addAttribute(MODEL_ATTRIBUTE_LOGIN,new LoginDTO());
        return MODEL_ATTRIBUTE_LOGIN;
    }
    @GetMapping("/logout")
    String logout(HttpSession session, Model model){
        logger.info("Logging out user!");
        session.invalidate();
        model.addAttribute(MODEL_ATTRIBUTE_LOGIN,new LoginDTO());
        return MODEL_ATTRIBUTE_LOGIN;
    }
    @GetMapping("/error")
    String error(Model model){
        logger.info("Error has occurred!");
        model.addAttribute(MODEL_ATTRIBUTE_LOGIN,new LoginDTO());
        return MODEL_ATTRIBUTE_LOGIN;
    }

    @PostMapping
    String logInUser(HttpSession session, Model model, String login, String password){
        logger.info("Logging in user!");
        var loggedUser = service.logInUser(login,password);
        if(loggedUser==null){
            model.addAttribute(MODEL_ATTRIBUTE_LOGIN,new LoginDTO());
            model.addAttribute("message","Invalid login or password");
            return MODEL_ATTRIBUTE_LOGIN;
        }
        session.setAttribute(SESSION_ATTRIBUTE_USER, loggedUser);
        return "redirect:/posts/";
    }
    @PostMapping(path = "/sign")
    String signUpUser(HttpSession session, Model model, @ModelAttribute("login") @Valid LoginDTO newLogin,
                      UserDTO user,  BindingResult bindingResult){
        logger.info("Signing up new user!");
        if(bindingResult.hasErrors()){
            model.addAttribute(MODEL_ATTRIBUTE_LOGIN,new LoginDTO());
            return MODEL_ATTRIBUTE_LOGIN;
        }
        service.createLogin(newLogin);
        UserDTO userDTO = service.signInUser(user);
        session.setAttribute(SESSION_ATTRIBUTE_USER, userDTO);
        return "redirect:/posts/";
    }
}
