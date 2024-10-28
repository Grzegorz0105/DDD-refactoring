package com.grzegorzkartasiewicz.comment;

import com.grzegorzkartasiewicz.post.Post;
import com.grzegorzkartasiewicz.post.PostId;
import com.grzegorzkartasiewicz.user.User;
import com.grzegorzkartasiewicz.user.UserId;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


public class Comment {

    private int id;
    private String time;

    private String description;

    private PostId postId;

    private UserId userId;


    public Comment() {
    }

    public Comment(String description, PostId postId, UserId userId) {
        this.description = description;
        this.postId = postId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public PostId getPostId() {
        return postId;
    }

    public UserId getUserId() {
        return userId;
    }

    @PrePersist
    void prePersist(){
        time = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT,FormatStyle.SHORT));
    }
}
