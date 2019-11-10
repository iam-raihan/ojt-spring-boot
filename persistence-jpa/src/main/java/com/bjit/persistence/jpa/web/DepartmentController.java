package com.bjit.persistence.jpa.web;

import com.bjit.persistence.jpa.Department;
import com.bjit.persistence.jpa.Employee;
import com.bjit.persistence.jpa.data.DepartmentRepository;
import com.bjit.persistence.jpa.data.IDepartmentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
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
    private DepartmentRepository departmentRepo;
    private IDepartmentDAO departmentDAO;

    @Autowired
    public DepartmentController(
            DepartmentRepository departmentRepo, IDepartmentDAO departmentDAO) {
        this.departmentRepo = departmentRepo;
        this.departmentDAO = departmentDAO;
    }

    @ModelAttribute(name = "department")
    public Department department() {
        return Department.builder().build();
    }

    @GetMapping
    public String showDepartmentForm(Model model) {
        List<Department> departments = new ArrayList<>();
        departmentRepo.findAll().forEach(i -> departments.add(i));
        model.addAttribute("departments", departments);
        return "departmentForm";
    }

    @GetMapping("findByName/{query}")
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
    }

    @PostMapping
    public String processDepartment(@Valid Department department, Errors errors, Model model) {
        if (errors.hasErrors()) {
            List<Department> departments = new ArrayList<>();
            departmentRepo.findAll().forEach(i -> departments.add(i));
            model.addAttribute("departments", departments);
            return "departmentForm";
        }
        departmentRepo.save(department);
        return "redirect:/department";
    }
}

