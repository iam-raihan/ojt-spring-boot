package com.bjit.raihan.services;

import com.bjit.raihan.entity.ItemEntity;
import com.bjit.raihan.repository.ItemRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@RequiredArgsConstructor
public class ItemService implements IService<ItemEntity, ItemRepository> {

    private final ItemRepository itemRepository;
    private final EntityManager entityManager;

    @Override
    public ItemRepository getRepository() {
        return itemRepository;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        entityManager.createNativeQuery("DELETE FROM MENU_ITEMS WHERE ITEM_ID = ?")
                .setParameter(1, id)
                .executeUpdate();

        itemRepository.deleteById(id);
    }
}
