package com.grzegorzkartasiewicz.login;

import com.grzegorzkartasiewicz.DomainEvent;
import com.grzegorzkartasiewicz.DomainEventPublisher;
import com.grzegorzkartasiewicz.login.vo.LoginCreator;
import com.grzegorzkartasiewicz.login.vo.LoginEvent;
import com.grzegorzkartasiewicz.login.vo.LoginId;
import com.grzegorzkartasiewicz.user.UserDTO;
import com.grzegorzkartasiewicz.user.UserFacade;
import com.grzegorzkartasiewicz.user.vo.UserCreator;
import com.grzegorzkartasiewicz.user.vo.UserId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;


public class LoginFacade {
    private static final Logger logger = LoggerFactory.getLogger(LoginFacade.class);
    private final LoginRepository repository;
    private final UserFacade userFacade;
    private final DomainEventPublisher publisher;

    LoginFacade(LoginRepository repository, UserFacade userFacade, DomainEventPublisher publisher) {
        this.repository = repository;
        this.userFacade = userFacade;
        this.publisher = publisher;
    }

    LoginDTO createLogin(LoginDTO loginDTO) {
        LoginCreator loginCreator = new LoginCreator(loginDTO.getId(), loginDTO.getNick(), loginDTO.getPassword(), loginDTO.getEmail());
        Login savedLogin = repository.save(Login.createFrom(loginCreator));
        LoginSnapshot savedLoginSnapshot = savedLogin.getSnapshot();
        publisher.publish(new LoginEvent(new LoginId(savedLoginSnapshot.getId()), DomainEvent.State.CREATED,
                new LoginEvent.LoginData(savedLoginSnapshot.getNick(), savedLoginSnapshot.getPassword(), savedLoginSnapshot.getEmail())));
        return LoginDTO.toDto(savedLogin);
    }

    UserDTO signInUser(UserDTO user) {
        UserCreator userCreator = new UserCreator(user.getName(), user.getSurname(), user.getAge(), user.getLogin());
        return userFacade.createUser(userCreator);
    }

    UserDTO logInUser(String nick, String password) {
        logger.info("Trying to log in user!");
        Optional<LoginSnapshot> loggedUser = repository.findAll().stream()
                .map(Login::getSnapshot)
                .filter(login -> login.getNick().equals(nick) && login.getPassword().equals(password))
                .findFirst();
        return loggedUser.map(login1 -> userFacade.getUser(login1.getUserId())).orElse(null);
    }

}
