package com.grzegorzkartasiewicz.post;

import com.grzegorzkartasiewicz.comment.Comment;
import com.grzegorzkartasiewicz.user.User;

import java.util.ArrayList;
import java.util.List;

public class PostDTO {
    private int id;
    private String time;

    private String description;

    private User user;

    private List<Comment> comments = new ArrayList<>();

    public PostDTO(int id, String time, String description, User user, List<Comment> comments) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public static PostDTO toDTO(Post post) {
        return new PostDTO(post.getId(), post.getTime(), post.getDescription(), post.getUser(), post.getComments());
    }
}
