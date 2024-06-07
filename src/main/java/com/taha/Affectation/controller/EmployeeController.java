//package com.taha.Affectation.controller;
//
//
//
//import com.taha.Affectation.model.Employee;
//import com.taha.Affectation.service.EmployeeService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/employees")
//public class EmployeeController {
//    private final EmployeeService employeeService;
//
//    public EmployeeController(EmployeeService employeeService) {
//        this.employeeService = employeeService;
//    }
//
//    @GetMapping
//    public String listEmployees(Model model) {
//        List<Employee> employees = employeeService.getAllEmployees();
//        model.addAttribute("employees", employees);
//        return "employee-list";
//    }
//}
//
//
