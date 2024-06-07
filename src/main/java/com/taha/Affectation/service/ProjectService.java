package com.taha.Affectation.service;

import com.taha.Affectation.model.Employee;
import com.taha.Affectation.model.Project;
import com.taha.Affectation.repository.EmployeeRepository;
import com.taha.Affectation.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;

    public ProjectService(ProjectRepository projectRepository, EmployeeRepository employeeRepository) {
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    public void assignEmployeeToProject(Long projectId, Long employeeId, Double implication) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Assign the employee to the project with the given implication
        project.getEmployees().add(employee);
        employee.getProjects().add(project);
        // Set the implication value in the join table or a separate entity

        projectRepository.save(project);
        employeeRepository.save(employee);
    }
}