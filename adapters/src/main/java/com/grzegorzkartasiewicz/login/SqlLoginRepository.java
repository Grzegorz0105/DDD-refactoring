package com.grzegorzkartasiewicz.login;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;


interface SqlLoginRepository extends Repository<Login,Integer> {
    List<Login> findAll();

    Optional<Login> findById(Integer id);

    Login save(Login entity);
}

@org.springframework.stereotype.Repository
class LoginRepositoryImpl implements LoginRepository {
    private final SqlLoginRepository repository;

    LoginRepositoryImpl(final SqlLoginRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Login> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Login> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Login save(Login entity) {
        return repository.save(entity);
    }
}