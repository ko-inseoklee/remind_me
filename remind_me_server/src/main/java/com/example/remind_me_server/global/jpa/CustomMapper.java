package com.example.remind_me_server.global.jpa;

public interface Mapper<Domain, Entity> {
    Domain toDomain(Entity entity);
    Entity toEntity(Domain domain);
    
}
