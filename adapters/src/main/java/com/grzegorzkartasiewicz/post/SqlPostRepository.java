package com.grzegorzkartasiewicz.post;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

interface SqlPostRepository extends Repository<PostSnapshot, Integer> {
    List<PostSnapshot> findAll();

    Optional<PostSnapshot> findById(Integer id);

    PostSnapshot save(PostSnapshot entity);

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
        return repository.findAll().stream().map(Post::restore).toList();
    }

    @Override
    public Optional<Post> findById(Integer id) {
        return repository.findById(id).map(Post::restore);
    }

    @Override
    public Post save(Post entity) {
        return Post.restore(repository.save(entity.getSnapshot()));
    }

    @Override
    public void deleteById(Integer integer) {
        repository.deleteById(integer);
    }
}