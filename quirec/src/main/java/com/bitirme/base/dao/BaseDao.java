package com.bitirme.base.dao;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@NoRepositoryBean
public interface BaseDao<E> extends JpaRepository<E, Long>, JpaSpecificationExecutor<E> {
}
