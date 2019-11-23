package com.bjit.raihan.seed;

import com.bjit.raihan.entity.BaseEntity;
import com.bjit.raihan.repository.IExtendedRepository;

import java.util.List;

public interface ISeeder<TEntity extends BaseEntity
        , TRepository extends IExtendedRepository<TEntity>> {

    TRepository repository();

    List<TEntity> getEntities();

    default void seed() {
        if (repository().isEmpty())
            repository().saveAll(getEntities());
    }
}
