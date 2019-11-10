package com.bjit.persistence.jpa.web;

import com.bjit.persistence.jpa.*;
import com.bjit.persistence.jpa.data.DepartmentRepository;
import com.bjit.persistence.jpa.data.EmployeeRepository;
import com.bjit.persistence.jpa.data.IngredientRepository;
import com.bjit.persistence.jpa.data.TacoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/employee")
@SessionAttributes("order")

public class EmployeeController {
    private EmployeeRepository employeeRepo;
    private DepartmentRepository departmentRepo;

    @Autowired
    public EmployeeController(
            DepartmentRepository departmentRepo,
            EmployeeRepository employeeRepo) {
        this.departmentRepo = departmentRepo;
        this.employeeRepo = employeeRepo;
    }

    @ModelAttribute(name = "employee")
    public Employee employee() {
        return Employee.builder().build();
    }

    @GetMapping
    public String showEmployeeForm(Model model) {
        List<Department> departments = new ArrayList<>();
        departmentRepo.findAll().forEach(i -> departments.add(i));

        List<Employee> employees = new ArrayList<>();
        employeeRepo.findAll().forEach(i -> employees.add(i));

        model.addAttribute("employees", employees);
        model.addAttribute("departments", departments);

        return "employeeForm";
    }

    @PostMapping
    public String processEmployee(@Valid Employee employee, Errors errors, Model model) {
        if (errors.hasErrors()) {
            List<Department> departments = new ArrayList<>();
            departmentRepo.findAll().forEach(i -> departments.add(i));

            List<Employee> employees = new ArrayList<>();
            employeeRepo.findAll().forEach(i -> employees.add(i));

            model.addAttribute("employees", employees);
            model.addAttribute("departments", departments);
            return "employeeForm";
        }
        employeeRepo.save(employee);

        return "redirect:/employee";
    }
}

