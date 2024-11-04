package com.grzegorzkartasiewicz.user;

import com.grzegorzkartasiewicz.comment.CommentDTO;
import com.grzegorzkartasiewicz.comment.vo.CommentId;
import com.grzegorzkartasiewicz.post.vo.PostCreator;
import com.grzegorzkartasiewicz.post.PostDTO;
import com.grzegorzkartasiewicz.post.PostFacade;
import com.grzegorzkartasiewicz.user.vo.UserCreator;
import com.grzegorzkartasiewicz.user.vo.UserId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserFacade {
    public static final Logger logger = LoggerFactory.getLogger(UserFacade.class);
    private final UserRepository repository;
    private final PostFacade postFacade;

    UserFacade(UserRepository repository, PostFacade postFacade) {
        this.repository = repository;
        this.postFacade = postFacade;
    }

    public PostDTO createPost(int id, String description) {
        logger.info("Creating post to save in DB!");
        return repository.findById(id).map(user -> {
            PostCreator targetPost = new PostCreator(description, new UserId(id));
            return postFacade.createPost(targetPost);
        }).orElseThrow(() -> new IllegalArgumentException("User with given id was not found!"));
    }

    CommentDTO createComment(UserDTO user, int postId, String description) {
        return postFacade.createComment(user, postId, description);
    }

    void deletePost(int postId) {
        postFacade.deletePost(postId);
    }

    void deleteComment(CommentId commentId) {
        postFacade.deleteComment(commentId);
    }

    public List<UserDTO> searchUsers(String search) {
        return repository.findAll().stream().map(UserDTO::toDTO)
                .filter(user -> user.getName().toLowerCase().contains(search.toLowerCase())
                        || user.getSurname().toLowerCase().contains(search.toLowerCase()))
                .toList();
    }

    public UserDTO createUser(UserCreator userCreator) {
        return UserDTO.toDTO(repository.save(User.createFrom(userCreator)));
    }

    public UserDTO getUser(UserId userId) {
        return UserDTO.toDTO(repository.findById(userId.id()).orElseThrow());
    }
}
