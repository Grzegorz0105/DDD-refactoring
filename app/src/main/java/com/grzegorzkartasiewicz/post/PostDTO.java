package com.grzegorzkartasiewicz.post;

import com.grzegorzkartasiewicz.comment.vo.CommentId;
import com.grzegorzkartasiewicz.user.vo.UserId;

import java.util.ArrayList;
import java.util.List;

public class PostDTO {
    private int id;

    private String description;

    private UserId user;

    private List<CommentId> comments = new ArrayList<CommentId>();

    public PostDTO() {
    }

    public PostDTO(int id, String description, UserId user, List<CommentId> comments) {
        this.id = id;
        this.description = description;
        this.user = user;
        this.comments = comments;
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

    public UserId getUser() {
        return user;
    }

    public void setUser(UserId user) {
        this.user = user;
    }

    public List<CommentId> getComments() {
        return comments;
    }

    public void setComments(List<CommentId> comments) {
        this.comments = comments;
    }

    static PostDTO toDTO(Post post) {
        PostSnapshot postSnapshot = post.getSnapshot();
        return new PostDTO(postSnapshot.getId(), postSnapshot.getDescription(), postSnapshot.getUserId(), postSnapshot.getCommentIds());
    }
}
