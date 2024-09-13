package com.grzegorzkartasiewicz.post;

import com.grzegorzkartasiewicz.comment.Comment;
import com.grzegorzkartasiewicz.comment.CommentFacade;
import com.grzegorzkartasiewicz.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

public class PostFacade {
    private static final Logger logger = LoggerFactory.getLogger(PostFacade.class);
    private final PostRepository repository;
    private final CommentFacade commentFacade;

    public PostFacade(PostRepository repository, CommentFacade commentFacade) {
        this.repository = repository;
        this.commentFacade = commentFacade;
    }

    public Post createPost(Post source){
        return repository.save(source);
    }
    public Comment createComment(User user, int postId,String description){
        logger.info("Creating comment to save in DB!");
        return repository.findById(postId).map(post -> {
            var targetComment = new Comment();
            targetComment.setDescription(description);
            targetComment.setPost(post);
            targetComment.setUser(user);
            return commentFacade.createComment(targetComment);
        }).orElseThrow(() ->new IllegalArgumentException("Post with given id was not found!"));
    }

    List<Post> searchPosts(String search) {
        logger.info("Searching for matching users and posts!");
        return repository.findAll().stream().filter(post -> post.getDescription().toLowerCase().contains(search.toLowerCase())).toList();
    }

    public void deleteComment(int commentId){
        commentFacade.deleteComment(commentId);
    }

    public void deletePost(int postId) {
        repository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Post with given id not found"))
                .getComments().forEach(comment -> commentFacade.deleteComment(comment.getId()));
        repository.deleteById(postId);
    }
}
