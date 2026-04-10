package com.example.remind_me_server.study.infra.persistence.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Repository;

import com.example.remind_me_server.study.application.port.out.AnswerRepository;
import com.example.remind_me_server.study.domain.Answer;
import com.example.remind_me_server.study.infra.persistence.entity.AnswerJpaEntity;
import com.example.remind_me_server.study.infra.persistence.mapper.AnswerMapper;
import com.example.remind_me_server.study.infra.persistence.repository.AnswerJpaRepository;

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
        AnswerJpaEntity e = mapper.toEntity(domain);
        jpaRepository.delete(e);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public long count() {
        return jpaRepository.count();
    }

    @Override
    public Iterable<Answer> findAll() {
        List<AnswerJpaEntity> entities = jpaRepository.findAll();

        return mapper.toDomainList(entities);
    }

    @Override
    public Iterable<Answer> findAllById(Iterable<Long> ids) {
        List<AnswerJpaEntity> entities = jpaRepository.findAllById(ids);

        return mapper.toDomainList(entities);
    }

    @Override
    public void deleteAll() {
        jpaRepository.deleteAll();
    }

    @Override
    public void deleteAll(Iterable<? extends Answer> domains) {
    List<Long> ids = StreamSupport.stream(domains.spliterator(), false)
        .map(answer -> answer.id()) // Answer로 자동 추론됨
        .toList();

        deleteAllById(ids);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        jpaRepository.deleteAllById(ids);
    }

    @Override
    public <S extends Answer> Iterable<S> saveAll(Iterable<S> domains) {
        List<AnswerJpaEntity> entities = StreamSupport.stream(domains.spliterator(), false)
            .map(answer -> mapper.toEntity(answer)) // Answer로 자동 추론됨
            .toList();

        entities = jpaRepository.saveAll(entities);
        
        return (Iterable<S>) mapper.toDomainList(entities);
    }
    
}
