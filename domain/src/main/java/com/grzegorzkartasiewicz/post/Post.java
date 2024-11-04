package com.grzegorzkartasiewicz.post;

import com.grzegorzkartasiewicz.post.vo.PostCreator;
import com.grzegorzkartasiewicz.user.vo.UserId;


class Post {

    static Post restore(PostSnapshot snapshot) {
        return new Post(
                snapshot.getId(),
                snapshot.getDescription(),
                snapshot.getUserId()
        );
    }

    static Post createFrom(final PostCreator source) {
        return new Post(
                0,
                source.description(),
                source.userId()
        );
    }

    private int id;

    private String description;

    private UserId userId;


    private Post(int id, String description, UserId userId) {
        this.id = id;
        this.description = description;
        this.userId = userId;
    }

    PostSnapshot getSnapshot() {
        return new PostSnapshot(id, description, userId);
    }

    Post edit(String description) {
        this.description = description;
        return this;
    }
}
