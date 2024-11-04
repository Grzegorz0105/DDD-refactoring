package com.grzegorzkartasiewicz.post;

import com.grzegorzkartasiewicz.user.vo.UserId;

public class PostDTO {
    private int id;

    private String description;

    private UserId user;

    public PostDTO() {
    }

    public PostDTO(int id, String description, UserId user) {
        this.id = id;
        this.description = description;
        this.user = user;
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

    static PostDTO toDTO(Post post) {
        PostSnapshot postSnapshot = post.getSnapshot();
        return new PostDTO(postSnapshot.getId(), postSnapshot.getDescription(), postSnapshot.getUserId());
    }
}
