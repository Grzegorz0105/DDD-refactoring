package com.grzegorzkartasiewicz.comment;

import com.grzegorzkartasiewicz.user.UserFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CommentConfigurator {

    @Bean
    CommentFacade commentFacade(final CommentRepository commentRepository, final UserFacade userFacade) {
        return new CommentFacade(commentRepository, userFacade);
    }
}
