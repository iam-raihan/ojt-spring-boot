package com.bjit.spring.security.data;

import com.bjit.spring.security.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IDepartmentDAO extends JpaRepository<Department, Long> {
    Department findByName(String name);

    @Query("SELECT f FROM Department f WHERE LOWER(f.name) = LOWER(:name)")
    List<Department> retrieveByName(@Param("name") String name);
}
