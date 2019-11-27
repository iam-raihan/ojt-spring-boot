package com.bjit.raihan.repository;

import com.bjit.raihan.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IExtendedRepository<TEntity extends BaseEntity>
        extends JpaRepository<TEntity, Long> {

//    @Query("SELECT CASE WHEN count(e) = 0 THEN true ELSE false END FROM TEntity e")
    default boolean isEmpty() {
        return count() == 0;
    }
}
