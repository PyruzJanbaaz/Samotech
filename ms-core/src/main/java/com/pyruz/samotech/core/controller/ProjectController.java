package com.pyruz.samotech.core.controller;

import com.pyruz.samotech.core.service.project.ProjectService;
import com.pyruz.samotech.shared.model.domain.project.NewProject;
import com.pyruz.samotech.shared.model.domain.project.UpdateProject;
import com.pyruz.samotech.shared.model.dto.base.BaseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ProjectController {

    final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/v1/project")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseDTO addProject(@Valid @RequestBody NewProject newProject) {
        return projectService.addProject(newProject);
    }

    @PutMapping("/v1/project")
    @ResponseStatus(HttpStatus.OK)
    public BaseDTO editProject(@Valid @RequestBody UpdateProject updateProject) {
        return projectService.editProject(updateProject);
    }

    @GetMapping("/v1/project/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseDTO getProject(@PathVariable Integer projectId) {
        return projectService.getProject(projectId);
    }

    @GetMapping("/v1/projects/collection/{collectionId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseDTO getProjectsByCollectionId(@PathVariable Integer collectionId) {
        return projectService.getProjectByCollectionId(collectionId);
    }

    @GetMapping("/v1/projects")
    @ResponseStatus(HttpStatus.OK)
    public BaseDTO getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/v1/projects/{page}")
    @ResponseStatus(HttpStatus.OK)
    public BaseDTO getProjects(@PathVariable Integer page) {
        return projectService.getProjects(page);
    }

    @DeleteMapping("/v1/project")
    @ResponseStatus(HttpStatus.OK)
    public BaseDTO deleteProject(@RequestParam Integer projectId) {
        return projectService.deleteProject(projectId);
    }

}
