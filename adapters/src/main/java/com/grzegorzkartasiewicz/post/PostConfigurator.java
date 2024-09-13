package com.grzegorzkartasiewicz.post;

import com.grzegorzkartasiewicz.comment.CommentFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class PostConfigurator {

    @Bean
    PostFacade postFacade(final PostRepository postRepository, final CommentFacade commentFacade) {
        return new PostFacade(postRepository, commentFacade);
    }
}
