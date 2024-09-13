package com.grzegorzkartasiewicz.login;

import com.grzegorzkartasiewicz.user.User;
import com.grzegorzkartasiewicz.user.UserDTO;
import com.grzegorzkartasiewicz.user.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoginFacade {
    private static final Logger logger = LoggerFactory.getLogger(LoginFacade.class);
    private final LoginRepository repository;
    private final UserFacade userFacade;

    LoginFacade(LoginRepository repository, UserFacade userFacade) {
        this.repository = repository;
        this.userFacade = userFacade;
    }

    User signInUser(User user){
        return userFacade.createUser(user);
    }

    User logInUser(String login,String password){
        logger.info("Trying to log in user!");
       var loggedUser = repository.findAll().stream()
               .filter(login1 -> login1.getNick().equals(login))
               .filter(login1 -> login1.getPassword().equals(password))
               .findFirst();
        return loggedUser.map(Login::getUser).orElse(null);
    }

}
