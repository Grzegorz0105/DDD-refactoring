package com.grzegorzkartasiewicz.comment;

import com.grzegorzkartasiewicz.comment.vo.CommentCreator;
import com.grzegorzkartasiewicz.comment.vo.CommentId;

public class CommentFacade {
    private final CommentRepository repository;

    CommentFacade(CommentRepository repository) {
        this.repository = repository;
    }


    public CommentDTO createComment(CommentCreator newComment){
        return CommentDTO.toDTO(repository.save(Comment.createFrom(newComment)));
    }

    public void deleteComment(CommentId commentId){
        repository.deleteById(commentId.id());
    }
}
