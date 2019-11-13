package com.bjit.spring.security.web;

import com.bjit.spring.security.data.DepartmentRepository;
import com.bjit.spring.security.data.EmployeeRepository;
import com.bjit.spring.security.Department;
import com.bjit.spring.security.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employee")
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

