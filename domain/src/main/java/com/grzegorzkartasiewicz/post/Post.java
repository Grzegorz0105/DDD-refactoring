package com.grzegorzkartasiewicz.post;

import com.grzegorzkartasiewicz.comment.CommentId;
import com.grzegorzkartasiewicz.user.UserId;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;


public class Post {

    private int id;
    private String time;

    private String description;

    private UserId userId;

    private List<CommentId> commentIds = new ArrayList<>();

    public Post() {
    }

    public Post(int id, String time, String description, UserId userId, List<CommentId> commentIds) {
        this.id = id;
        this.time = time;
        this.description = description;
        this.userId = userId;
        this.commentIds = commentIds;
    }

    public Post(String description, UserId userId) {
        this.description = description;
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

    public UserId getUserId() {
        return userId;
    }

    public List<CommentId> getCommentIds() {
        return commentIds;
    }

    @PrePersist
    void prePersist(){
        time = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT,FormatStyle.SHORT));
    }
}
