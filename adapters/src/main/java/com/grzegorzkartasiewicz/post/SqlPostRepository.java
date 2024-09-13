package com.grzegorzkartasiewicz.post;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

interface SqlPostRepository extends Repository<Post,Integer> {
    List<Post> findAll();

    Optional<Post> findById(Integer id);

    Post save(Post entity);

    void deleteById(Integer integer);
}


@org.springframework.stereotype.Repository
class PostRepositoryImpl implements PostRepository {
    private final SqlPostRepository repository;

    PostRepositoryImpl(final SqlPostRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Post> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Post> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Post save(Post entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteById(Integer integer) {
        repository.deleteById(integer);
    }
}