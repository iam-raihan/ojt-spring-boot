package com.bjit.persistence.jpa.data;

import com.bjit.persistence.jpa.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository
         extends CrudRepository<Employee, Long> {

}
