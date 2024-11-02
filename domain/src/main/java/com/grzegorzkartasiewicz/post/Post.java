package com.grzegorzkartasiewicz.post;

import com.grzegorzkartasiewicz.comment.vo.CommentId;
import com.grzegorzkartasiewicz.post.vo.PostCreator;
import com.grzegorzkartasiewicz.user.vo.UserId;


import java.util.ArrayList;
import java.util.List;


class Post {

    static Post restore(PostSnapshot snapshot) {
        return new Post(
                snapshot.getId(),
                snapshot.getDescription(),
                snapshot.getUserId(),
                snapshot.getCommentIds()
        );
    }

    static Post createFrom(final PostCreator source) {
        return new Post(
                0,
                source.description(),
                source.userId(),
                null
        );
    }

    private int id;

    private String description;

    private UserId userId;

    private List<CommentId> commentIds = new ArrayList<>();


    private Post(int id, String description, UserId userId, List<CommentId> commentIds) {
        this.id = id;
        this.description = description;
        this.userId = userId;
        this.commentIds = commentIds;
    }
}
