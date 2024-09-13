package com.grzegorzkartasiewicz.comment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CommentConfigurator {

    @Bean
    CommentFacade commentFacade(final CommentRepository commentRepository) {
        return new CommentFacade(commentRepository);
    }
}
