package com.taha.Affectation.controller;



import com.taha.Affectation.model.Employee;
import com.taha.Affectation.model.Project;
import com.taha.Affectation.service.EmployeeService;
import com.taha.Affectation.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;
    private final EmployeeService employeeService;

    public ProjectController(ProjectService projectService, EmployeeService employeeService) {
        this.projectService = projectService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public String listProjects(Model model) {
        List<Project> projects = projectService.getAllProjects();
        model.addAttribute("projects", projects);
        return "project-list";
    }

    @GetMapping("/{id}")
    public String viewProject(@PathVariable Long id, Model model) {
        Project project = projectService.getProjectById(id);
        model.addAttribute("project", project);
        return "project-details";
    }

    @GetMapping("/{id}/assign")
    public String assignEmployeeForm(@PathVariable Long id, Model model) {
        Project project = projectService.getProjectById(id);
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("project", project);
        model.addAttribute("employees", employees);
        return "assign-employee";
    }

    @PostMapping("/{id}/assign")
    public String assignEmployee(@PathVariable Long id, @RequestParam Long employeeId, @RequestParam Double implication) {
        projectService.assignEmployeeToProject(id, employeeId, implication);
        return "redirect:/projects/" + id;
    }
}