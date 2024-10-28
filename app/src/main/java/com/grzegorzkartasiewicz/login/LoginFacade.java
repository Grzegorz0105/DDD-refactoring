package com.grzegorzkartasiewicz.login;

import com.grzegorzkartasiewicz.user.UserDTO;
import com.grzegorzkartasiewicz.user.UserFacade;
import com.grzegorzkartasiewicz.user.UserId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;


public class LoginFacade {
    private static final Logger logger = LoggerFactory.getLogger(LoginFacade.class);
    private final LoginRepository repository;
    private final UserFacade userFacade;

    LoginFacade(LoginRepository repository, UserFacade userFacade) {
        this.repository = repository;
        this.userFacade = userFacade;
    }

    UserId signInUser(UserId user){
        return userFacade.createUser(user);
    }

    UserDTO logInUser(String login, String password){
        logger.info("Trying to log in user!");
        Optional<Login> loggedUser = repository.findAll().stream()
               .filter(login1 -> login1.getNick().equals(login))
               .filter(login1 -> login1.getPassword().equals(password))
               .findFirst();
        return loggedUser.map(login1 -> userFacade.getUser(login1.getUserId())).orElse(null);
    }

}
