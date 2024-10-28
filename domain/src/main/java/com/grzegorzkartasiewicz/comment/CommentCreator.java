package com.grzegorzkartasiewicz.comment;

import com.grzegorzkartasiewicz.post.PostId;
import com.grzegorzkartasiewicz.user.UserId;

public record CommentCreator(String description, PostId postId, UserId userId) {
    public Comment toEntity() {
        return new Comment(description(), postId(), userId());
    }
}
