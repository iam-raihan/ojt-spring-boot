package com.bjit.spring.security.data;

import com.bjit.spring.security.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Long> {

}
