package com.bjit.raihan.services;

import com.bjit.raihan.entity.BaseEntity;
import com.bjit.raihan.repository.IExtendedRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

// this is why i love "strongly typed" languages!
public interface IService<TEntity extends BaseEntity
        , TRepository extends IExtendedRepository<TEntity>> {

    TRepository getRepository();

    default List<TEntity> findAll() {
        return getRepository().findAll();
    }

    default TEntity findById(Long id) throws EntityNotFoundException {
        TEntity result = getRepository().findById(id)
                .orElseThrow(() -> new EntityNotFoundException("khuija pailam nah!"));

        return result;
    }

    default TEntity save(TEntity entity) {
        entity = getRepository().save(entity);
        return entity;
    }

    /***
     * there's a problem here.
     * user can manipulate createdAt date or other attributes.
     * TODO - implement DTO.
     */
    default TEntity update(TEntity entity, Long id) {
        TEntity result = findById(id);
        entity.setId(id);
        entity.setCreatedAt(result.getCreatedAt());
        entity.setIsDeleted(result.getIsDeleted());
        return save(entity);
    }

    default void deleteById(Long id) {
        if (getRepository().existsById(id))
            getRepository().deleteById(id);
        else
            throw new EntityNotFoundException("khuija pailam nah!");
    }
}
