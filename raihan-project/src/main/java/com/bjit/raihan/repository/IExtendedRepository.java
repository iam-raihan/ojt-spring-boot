package com.bjit.raihan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IExtendedRepository<TEntity, TKey> extends JpaRepository<TEntity, TKey> {

//    @Query("SELECT CASE WHEN count(e) = 0 THEN true ELSE false END FROM TEntity e")
    default boolean isEmpty() {
        return count() == 0;
    }
}
