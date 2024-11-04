package com.grzegorzkartasiewicz.comment;

import com.grzegorzkartasiewicz.comment.vo.CommentCreator;
import com.grzegorzkartasiewicz.comment.vo.CommentId;
import com.grzegorzkartasiewicz.post.vo.PostId;

public class CommentFacade {
    private final CommentRepository repository;

    CommentFacade(CommentRepository repository) {
        this.repository = repository;
    }


    public CommentDTO createComment(CommentCreator newComment){
        Comment savedComment = repository.save(Comment.createFrom(newComment));
        return CommentDTO.toDTO(savedComment);
    }

    public void deleteComment(CommentId commentId){
        repository.deleteById(commentId.id());
    }

    public void deleteCommentsForPost(PostId postId){
        repository.findAll().stream().map(Comment::getSnapshot)
                .filter(comment -> comment.getPostId().id() == postId.id())
                .forEach(comment -> repository.deleteById(comment.getId()));
    }
}
