package com.example.remind_me_server.study.infra.persistence.adapter;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.remind_me_server.study.domain.Answer;
import com.example.remind_me_server.study.infra.persistence.entity.AnswerJpaEntity;
import com.example.remind_me_server.study.infra.persistence.mapper.AnswerMapper;
import com.example.remind_me_server.study.infra.persistence.repository.AnswerJpaRepository;
import com.example.remind_me_server.study.repository.AnswerRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AnswerAdapter implements AnswerRepository {

    private final AnswerJpaRepository jpaRepository;
    private final AnswerMapper mapper;

    @Override
    public Optional<Answer> findById(Long id) {
        Optional<AnswerJpaEntity> e = jpaRepository.findById(id);

        return e.map(mapper::toDomain);
    }

    @Override
    public Answer save(Answer domain) {
        
        AnswerJpaEntity e = mapper.toEntity(domain);

        e = jpaRepository.save(e);

        return mapper.toDomain(e);
    }

    @Override
    public void delete(Answer domain) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public boolean existsById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existsById'");
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'count'");
    }

    @Override
    public Iterable<Answer> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Iterable<Answer> findAllById(Iterable<Long> ids) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllById'");
    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAll'");
    }

    @Override
    public void deleteAll(Iterable<? extends Answer> entities) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAll'");
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAllById'");
    }

    @Override
    public <S extends Answer> Iterable<S> saveAll(Iterable<S> entities) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveAll'");
    }
    
}
