package com.bjit.raihan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IExtendedRepository<TEntity, TKey> extends JpaRepository<TEntity, TKey> {

}
