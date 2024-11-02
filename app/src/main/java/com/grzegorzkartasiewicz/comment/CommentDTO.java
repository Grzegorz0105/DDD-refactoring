package com.grzegorzkartasiewicz.comment;

import com.grzegorzkartasiewicz.post.vo.PostId;
import com.grzegorzkartasiewicz.user.vo.UserId;

public class CommentDTO {
    private int id;

    private String description;

    private PostId postId;

    private UserId userId;

    public CommentDTO(int id, String description, PostId postId, UserId userId) {
        this.id = id;
        this.description = description;
        this.postId = postId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PostId getPostId() {
        return postId;
    }

    public void setPostId(PostId postId) {
        this.postId = postId;
    }

    public UserId getUserId() {
        return userId;
    }

    public void setUserId(UserId userId) {
        this.userId = userId;
    }

    public static CommentDTO toDTO(Comment comment) {
        return new CommentDTO(comment.getId(), comment.getDescription(), comment.getPostId(), comment.getUserId());
    }
}
