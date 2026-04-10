package com.example.remind_me_server.global.jpa;

import java.util.List;

public interface CustomMapper<Domain, Entity> {
    Domain toDomain(Entity entity);
    Entity toEntity(Domain domain);
    Iterable<Domain> toDomainList(List<Entity> entities);
}
