package com.user.library.crud.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface BaseJpaRepository<Entity> extends JpaRepository<Entity, Integer>, JpaSpecificationExecutor<Entity> {
}
