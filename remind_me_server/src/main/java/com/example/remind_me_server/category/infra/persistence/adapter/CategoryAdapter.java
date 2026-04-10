package com.example.remind_me_server.category.infra.persistence.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Repository;

import com.example.remind_me_server.category.application.port.out.CategoryRepository;
import com.example.remind_me_server.category.domain.Category;
import com.example.remind_me_server.category.infra.persistence.entity.CategoryJpaEntity;
import com.example.remind_me_server.category.infra.persistence.mapper.CategoryMapper;
import com.example.remind_me_server.category.infra.persistence.repository.CategoryJpaRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CategoryAdapter implements CategoryRepository {
    
    private final CategoryJpaRepository jpaRepository;
    private final CategoryMapper mapper;

    @Override
    public Optional<Category> findById(Long id) {
        Optional<CategoryJpaEntity> e = jpaRepository.findById(id);

        return e.map(mapper::toDomain);
    }

    @Override
    public Category save(Category domain) {
        CategoryJpaEntity e = mapper.toEntity(domain);

        e = jpaRepository.save(e);

        return mapper.toDomain(e);
    }

    @Override
    public void delete(Category domain) {
        if(domain.id() == null) throw new IllegalArgumentException("ID는 필수입니다.");

        deleteById(domain.id());
    }

    @Override
    public void deleteById(@NotNull Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(@NotNull Long id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public long count() {
        return jpaRepository.count();
    }

    @Override
    public Iterable<Category> findAll() {
        List<CategoryJpaEntity> entities = jpaRepository.findAll();

        return mapper.toDomainList(entities);
    }

    @Override
    public Iterable<Category> findAllById(Iterable<Long> ids) {
        List<CategoryJpaEntity> entities = jpaRepository.findAllById(ids);

        return mapper.toDomainList(entities);
    }

    @Override
    public void deleteAll() {
        jpaRepository.deleteAll();
    }

    @Override
    public void deleteAll(Iterable<? extends Category> domains) {
        List<Long> ids = StreamSupport.stream(domains.spliterator(), false)
            .map(category -> category.id())
            .toList();
        
        deleteAllById(ids);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        jpaRepository.deleteAllById(ids);
    }

    @Override
    public <S extends Category> Iterable<S> saveAll(Iterable<S> domains) {
        List<CategoryJpaEntity> entities = StreamSupport.stream(domains.spliterator(), false)
            .map(c -> mapper.toEntity(c))
            .toList();
        
        entities = jpaRepository.saveAll(entities);

        return (Iterable<S>) mapper.toDomainList(entities);
    }

    @Override
    public Category findByName(String categoryName, Long userId) {
        Optional<CategoryJpaEntity> e =  jpaRepository.findByNameAndUserId(categoryName, userId);

        if(e.isPresent()) return mapper.toDomain(e.get());

        throw new EntityNotFoundException();
    }
    
    
}
