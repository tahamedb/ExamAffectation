package com.taha.Affectation.service;

import com.taha.Affectation.model.Employee;
import com.taha.Affectation.model.Project;
import com.taha.Affectation.repository.EmployeeRepository;
import com.taha.Affectation.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

}