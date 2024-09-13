package com.grzegorzkartasiewicz.user;

import com.grzegorzkartasiewicz.comment.Comment;
import com.grzegorzkartasiewicz.post.Post;
import com.grzegorzkartasiewicz.post.PostFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

public class UserFacade {
    public static final Logger logger = LoggerFactory.getLogger(UserFacade.class);
    private UserRepository repository;
    private PostFacade postFacade;

    public UserFacade(UserRepository repository, PostFacade postFacade) {
        this.repository = repository;
        this.postFacade = postFacade;
    }

    public Post createPost(int id, String description){
        logger.info("Creating post to save in DB!");
        return repository.findById(id).map(user -> {
            var targetPost = new Post();
            targetPost.setDescription(description);
            targetPost.setUser(user);
            return postFacade.createPost(targetPost);
        }).orElseThrow(() ->new IllegalArgumentException("User with given id was not found!"));
    }
    Comment createComment(User user, int postId, String description){
        return postFacade.createComment(user, postId, description);
    }
    void deletePost(int postId){
        postFacade.deletePost(postId);
    }
    void deleteComment(int commentId){
        postFacade.deleteComment(commentId);
    }

    public List<User> searchUsers(String search) {
        return repository.findAll().stream().filter(user -> user.getName().toLowerCase().contains(search.toLowerCase()) || user.getSurname().toLowerCase().contains(search.toLowerCase())).toList();
    }

    public User createUser(User user) {
        return repository.save(user);
    }
}
