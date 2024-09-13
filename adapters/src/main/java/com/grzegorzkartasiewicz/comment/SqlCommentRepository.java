package com.grzegorzkartasiewicz.comment;



import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;


interface SqlCommentRepository extends Repository<Comment,Integer> {
    List<Comment> findAll();

    Optional<Comment> findById(Integer id);

    Comment save(Comment entity);

    void deleteById(Integer integer);
}

@org.springframework.stereotype.Repository
class CommentRepositoryImpl implements CommentRepository {
    private final SqlCommentRepository repository;

    CommentRepositoryImpl(final SqlCommentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Comment> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Comment> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Comment save(Comment entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteById(Integer integer) {
        repository.deleteById(integer);
    }
}
