package com.grzegorzkartasiewicz.post;

import com.grzegorzkartasiewicz.comment.vo.CommentId;
import com.grzegorzkartasiewicz.user.vo.UserId;

import java.util.ArrayList;
import java.util.List;

class PostSnapshot {

    private int id;

    private String description;

    private UserId userId;

    private List<CommentId> commentIds = new ArrayList<>();

    protected PostSnapshot() {
    }

    PostSnapshot(int id, String description, UserId userId, List<CommentId> commentIds) {
        this.id = id;
        this.description = description;
        this.userId = userId;
        this.commentIds = commentIds;
    }

    public int getId() {
        return id;
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
}
