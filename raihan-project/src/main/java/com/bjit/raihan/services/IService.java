package com.bjit.raihan.services;

import com.bjit.raihan.entity.BaseEntity;
import com.bjit.raihan.repository.IExtendedRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

// this is why i love "strongly typed" languages!
public interface IService<TEntity extends BaseEntity
        , TRepository extends IExtendedRepository<TEntity, Long>> {

    TRepository getRepository();

    default List<TEntity> findAll() {
        return getRepository().findAll();
    }

    default TEntity findById(Long id) throws EntityNotFoundException {
        Optional<TEntity> result = getRepository().findById(id);
        if (result.isPresent())
            return result.get();

        throw new EntityNotFoundException(result.toString());
    }

    default TEntity save(TEntity entity) {
        getRepository().save(entity);
        return entity;
    }

    default void deleteById(Long id) {
        getRepository().deleteById(id);
    }
}
