package com.grzegorzkartasiewicz.post;

import com.grzegorzkartasiewicz.comment.vo.CommentCreator;
import com.grzegorzkartasiewicz.comment.CommentDTO;
import com.grzegorzkartasiewicz.comment.CommentFacade;
import com.grzegorzkartasiewicz.comment.vo.CommentId;
import com.grzegorzkartasiewicz.post.vo.PostCreator;
import com.grzegorzkartasiewicz.post.vo.PostId;
import com.grzegorzkartasiewicz.user.UserDTO;
import com.grzegorzkartasiewicz.user.vo.UserId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PostFacade {
    private static final Logger logger = LoggerFactory.getLogger(PostFacade.class);
    private final PostRepository repository;
    private final CommentFacade commentFacade;

    PostFacade(PostRepository repository, CommentFacade commentFacade) {
        this.repository = repository;
        this.commentFacade = commentFacade;
    }

    public PostDTO createPost(PostCreator source){
        return PostDTO.toDTO(repository.save(Post.createFrom(source)));
    }
    public CommentDTO createComment(UserDTO user, int postId, String description){
        logger.info("Creating comment to save in DB!");
        return repository.findById(postId).map(post -> {
            var targetComment = new CommentCreator(description, new PostId(postId), new UserId(user.getId()));
            return commentFacade.createComment(targetComment);
        }).orElseThrow(() ->new IllegalArgumentException("Post with given id was not found!"));
    }

    public PostDTO editPost(PostId postId, String description) {
        logger.info("Edit post with id: {}", postId.id());
        return repository.findById(postId.id()).map(post -> post.edit(description)).map(PostDTO::toDTO).orElse(null);
    }

    List<PostDTO> searchPosts(String search) {
        logger.info("Searching for matching users and posts!");
        return repository.findAll().stream()
                .map(Post::getSnapshot)
                .filter(post -> post.getDescription().toLowerCase().contains(search.toLowerCase()))
                .map(postSnapshot -> PostDTO.toDTO(Post.restore(postSnapshot)))
                .toList();
    }

    public void deleteComment(CommentId commentId){
        commentFacade.deleteComment(commentId);
    }

    public void deletePost(int postId) {
        repository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Post with given id not found"))
                .getSnapshot()
                .getCommentIds().forEach(commentFacade::deleteComment);
        repository.deleteById(postId);
    }
}
