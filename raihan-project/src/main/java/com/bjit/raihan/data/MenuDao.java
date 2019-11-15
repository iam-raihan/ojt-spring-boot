package com.bjit.raihan.data;

import com.bjit.raihan.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuDao extends JpaRepository<MenuEntity, Integer> {
}
