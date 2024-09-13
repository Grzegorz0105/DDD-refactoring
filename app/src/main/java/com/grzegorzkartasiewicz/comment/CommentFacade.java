package com.grzegorzkartasiewicz.comment;

public class CommentFacade {
    private final CommentRepository repository;

    public CommentFacade(CommentRepository repository) {
        this.repository = repository;
    }


    public Comment createComment(Comment newComment){
        return repository.save(newComment);
    }

    public void deleteComment(int commentId){
        repository.deleteById(commentId);
    }
}
