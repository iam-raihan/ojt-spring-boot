package com.bjit.spring.security.data;

import com.bjit.spring.security.Department;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	public List<Department> findAll(){
		return (List<Department>) departmentRepository.findAll();
	}
	
	@Cacheable(cacheNames = "departments",key = "#id")
	public Department findById(Long id){
		return departmentRepository.findById(id).get();
	}
	
	@CachePut(cacheNames = "departments",key = "#department.id")
	public Department save(Department department){
		return departmentRepository.save(department);
		
	}
	
	@CacheEvict(value="departments", allEntries=true)
	public void clearCache(){
	}
	

}
