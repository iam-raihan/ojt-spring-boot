package com.bjit.spring.security.web;

import com.bjit.spring.security.Department;
import com.bjit.spring.security.data.DepartmentRepository;
import com.bjit.spring.security.data.DepartmentService;
import com.bjit.spring.security.data.IDepartmentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentController {
	@Autowired
    private DepartmentService departmentService;
    private IDepartmentDAO departmentDAO;

    @Autowired
    public DepartmentController(IDepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    @ModelAttribute(name = "department")
    public Department department() {
        return Department.builder().build();
    }

    @GetMapping
    public String showDepartmentForm(Model model) {
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        return "departmentForm";
    }
    
    @GetMapping("edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
    	List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
    	
        Department department = departmentService.findById(id);
        model.addAttribute("department", department);
        return "departmentForm";
    }

    /*@GetMapping("findByName/{query}")
    @ResponseBody
    public ResponseEntity<Department> findByName(@PathVariable("query") String query) {
        return ResponseEntity.status(200).body(departmentDAO.findByName(query));
    }

    @GetMapping("retrieveByName/{query}")
    @ResponseBody
    public ResponseEntity<List<Department>> retrieveByName(@PathVariable("query") String query) {
        return ResponseEntity.status(200).body(departmentDAO.retrieveByName(query));
    }
    
    @GetMapping("findAllByPaging/page/{page}/count/{count}")
    @ResponseBody
    public ResponseEntity<List<Department>> findAllByPaging(@PathVariable("page") Integer page,
    		@PathVariable("count") Integer count) {
        return ResponseEntity.status(200).body(departmentDAO.findAll(PageRequest.of(page, count)).getContent());
    }*/

    @PostMapping
    public String processDepartment(@Valid Department department, Errors errors, Model model) {
    	departmentService.clearCache();
        if (errors.hasErrors()) {
        	List<Department> departments = departmentService.findAll();
            model.addAttribute("departments", departments);
            return "departmentForm";
        }
        departmentService.save(department);
        return "redirect:/department";
    }
}

