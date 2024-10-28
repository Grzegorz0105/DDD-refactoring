package com.grzegorzkartasiewicz.comment;

public class CommentFacade {
    private final CommentRepository repository;

    public CommentFacade(CommentRepository repository) {
        this.repository = repository;
    }


    public Comment createComment(CommentCreator newComment){
        return repository.save(newComment.toEntity());
    }

    public void deleteComment(CommentId commentId){
        repository.deleteById(commentId.id());
    }
}
