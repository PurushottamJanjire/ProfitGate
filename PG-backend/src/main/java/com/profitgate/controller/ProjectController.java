package com.profitgate.controller;

import com.profitgate.dto.ApplyProjectRequest;
import com.profitgate.dto.CreateProjectRequest;
import com.profitgate.entity.*;
import com.profitgate.repository.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectRepository projectRepo;
    private final CompanyRepository companyRepo;
    private final ProjectApplicationRepository applicationRepo;

    public ProjectController(ProjectRepository projectRepo, CompanyRepository companyRepo, ProjectApplicationRepository applicationRepo) {
        this.projectRepo = projectRepo;
        this.companyRepo = companyRepo;
        this.applicationRepo = applicationRepo;
    }

    @PostMapping
    public Project create(@RequestBody @Valid CreateProjectRequest req) {
        Company company = companyRepo.findById(req.companyId())
                .orElseThrow(() -> new NoSuchElementException("Company not found"));
        Project p = new Project();
        p.setCompany(company);
        p.setTitle(req.title());
        p.setDescription(req.description());
        p.setBudget(req.budget());
        p.setStatus(Project.Status.open);
        return projectRepo.save(p);
    }

    @GetMapping
    public Page<Project> list(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size,
                              @RequestParam(required = false) Integer companyId) {
        PageRequest pr = PageRequest.of(page, size);
        if (companyId != null) {
            Company company = companyRepo.findById(companyId)
                    .orElseThrow(() -> new NoSuchElementException("Company not found"));
            return projectRepo.findByCompany(company, pr);
        }
        return projectRepo.findAll(pr);
    }

    @PostMapping("/apply")
    public ProjectApplication apply(@RequestBody @Valid ApplyProjectRequest req) {
        Project project = projectRepo.findById(req.projectId())
                .orElseThrow(() -> new NoSuchElementException("Project not found"));
        Company company = companyRepo.findById(req.companyId())
                .orElseThrow(() -> new NoSuchElementException("Company not found"));
        ProjectApplication app = new ProjectApplication();
        app.setProject(project);
        app.setCompany(company);
        app.setMessage(req.message());
        app.setStatus(ProjectApplication.Status.pending);
        return applicationRepo.save(app);
    }
}
