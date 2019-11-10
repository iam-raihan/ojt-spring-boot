package com.bjit.persistence.jpa.data;

import com.bjit.persistence.jpa.Department;
import com.bjit.persistence.jpa.Employee;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository
         extends CrudRepository<Department, Long> {

}
