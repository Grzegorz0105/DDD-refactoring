package com.grzegorzkartasiewicz.user;


import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

interface SqlUserRepository extends Repository<User,Integer> {
    List<User> findAll();

    Optional<User> findById(Integer id);

    UserId save(UserId entity);
}

@org.springframework.stereotype.Repository
class UserRepositoryImpl implements UserRepository {
    private final SqlUserRepository repository;

    UserRepositoryImpl(final SqlUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> findAll() {
        return  repository.findAll();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public UserId save(UserId entity) {
        return repository.save(entity);
    }
}