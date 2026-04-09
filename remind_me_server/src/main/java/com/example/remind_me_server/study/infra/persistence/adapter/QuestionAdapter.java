package com.example.remind_me_server.study.infra.persistence.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Repository;

import com.example.remind_me_server.study.application.port.out.QuestionRepository;
import com.example.remind_me_server.study.domain.Question;
import com.example.remind_me_server.study.infra.persistence.entity.QuestionJpaEntity;
import com.example.remind_me_server.study.infra.persistence.mapper.QuestionMapper;
import com.example.remind_me_server.study.infra.persistence.repository.QuestionJpaRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QuestionAdapter implements QuestionRepository {
    private final QuestionJpaRepository jpaRepository;
    private final QuestionMapper mapper;

    @Override
    public Optional<Question> findById(Long id) {
        Optional<QuestionJpaEntity> e = jpaRepository.findById(id);

        return e.map(mapper::toDomain);
    }

    @Override
    public Question save(Question domain) {
        QuestionJpaEntity e = mapper.toEntity(domain);
        
        e = jpaRepository.save(e);

        return mapper.toDomain(e);
    }

    @Override
    public void delete(Question domain) {
        QuestionJpaEntity e = mapper.toEntity(domain);

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
    public Iterable<Question> findAll() {
        
        List<QuestionJpaEntity> entities = jpaRepository.findAll();

        return mapper.toDomainList(entities);

    }

    @Override
    public Iterable<Question> findAllById(Iterable<Long> ids) {
        List<QuestionJpaEntity> entities = jpaRepository.findAllById(ids);

        return mapper.toDomainList(entities);
    }

    @Override
    public void deleteAll() {

        jpaRepository.deleteAll();
    }

    @Override
    public void deleteAll(Iterable<? extends Question> domains) {
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
    public <S extends Question> Iterable<S> saveAll(Iterable<S> domains) {
        List<QuestionJpaEntity> entities = StreamSupport.stream(domains.spliterator(), false)
            .map(question -> mapper.toEntity(question)) // Answer로 자동 추론됨
            .toList();

        entities = jpaRepository.saveAll(entities);
        
        return (Iterable<S>) mapper.toDomainList(entities);
    }
    
}
