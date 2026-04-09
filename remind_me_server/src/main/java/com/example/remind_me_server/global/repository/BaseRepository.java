package com.example.remind_me_server.global.repository;

import java.util.Optional;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T> {
    Optional<T> findById(Long id);

    T save(T domain);

    void delete(T domain);

    void deleteById(Long id);

    boolean existsById(Long id);

    long count();

    Iterable<T> findAll();

    Iterable<T> findAllById(Iterable<Long> ids);

    void deleteAll();

    void deleteAll(Iterable<? extends T> domains);

    void deleteAllById(Iterable<? extends Long> ids);

    <S extends T> Iterable<S> saveAll(Iterable<S> domains);
}
