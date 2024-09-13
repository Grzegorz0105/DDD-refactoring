package com.grzegorzkartasiewicz.user;

import com.grzegorzkartasiewicz.post.PostFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UserConfigurator {

    @Bean
    UserFacade userFacade(final UserRepository userRepository, final PostFacade postFacade) {
        return new UserFacade(userRepository, postFacade);
    }
}
