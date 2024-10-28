package com.grzegorzkartasiewicz.post;

import com.grzegorzkartasiewicz.user.UserId;

public record PostCreator(String description, UserId userId) {
    public Post toEntity() {
        return  new Post(description(), userId());
    }
}
