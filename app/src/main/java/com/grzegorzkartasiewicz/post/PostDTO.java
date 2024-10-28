package com.grzegorzkartasiewicz.post;

import com.grzegorzkartasiewicz.comment.CommentId;
import com.grzegorzkartasiewicz.user.UserId;

import java.util.ArrayList;
import java.util.List;

public class PostDTO {
    private int id;
    private String time;

    private String description;

    private UserId user;

    private List<CommentId> comments = new ArrayList<CommentId>();

    public PostDTO(int id, String time, String description, UserId user, List<CommentId> comments) {
        this.id = id;
        this.time = time;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public static PostDTO toDTO(Post post) {
        return new PostDTO(post.getId(), post.getTime(), post.getDescription(), post.getUserId(), post.getCommentIds());
    }
}
