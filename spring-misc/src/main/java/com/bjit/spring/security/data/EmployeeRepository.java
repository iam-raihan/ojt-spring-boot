package com.bjit.spring.security.data;

import com.bjit.spring.security.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository
         extends CrudRepository<Employee, Long> {

}
