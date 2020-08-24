package com.pyruz.samotech.core.controller;

import com.pyruz.samotech.core.service.project.ProjectService;
import com.pyruz.samotech.shared.model.domain.project.NewProject;
import com.pyruz.samotech.shared.model.domain.project.UpdateProject;
import com.pyruz.samotech.shared.model.dto.base.BaseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<BaseDTO> addProject(@Valid @RequestBody NewProject newProject) {
        return new ResponseEntity<>(projectService.addProject(newProject), HttpStatus.CREATED);
    }

    @PutMapping("/v1/project")
    public ResponseEntity<BaseDTO> editProject(@Valid @RequestBody UpdateProject updateProject) {
        return new ResponseEntity<>(projectService.editProject(updateProject), HttpStatus.OK);
    }

    @GetMapping("/v1/project/{projectId}")
    public ResponseEntity<BaseDTO> getProject(@PathVariable Integer projectId) {
        return new ResponseEntity<>(projectService.getProject(projectId), HttpStatus.OK);
    }

    @GetMapping("/v1/projects/collection/{collectionId}")
    public ResponseEntity<BaseDTO> getProjectsByCollectionId(@PathVariable Integer collectionId) {
        return new ResponseEntity<>(projectService.getProjectByCollectionId(collectionId), HttpStatus.OK);
    }

    @GetMapping("/v1/projects")
    public ResponseEntity<BaseDTO> getAllProjects() {
        return new ResponseEntity<>(projectService.getAllProjects(), HttpStatus.OK);
    }

    @GetMapping("/v1/projects/{page}")
    public ResponseEntity<BaseDTO> getProjects(@PathVariable Integer page) {
        return new ResponseEntity<>(projectService.getProjects(page), HttpStatus.OK);
    }

    @DeleteMapping("/v1/project")
    public ResponseEntity<BaseDTO> deleteProject(@RequestParam Integer projectId) {
        return new ResponseEntity<>(projectService.deleteProject(projectId), HttpStatus.OK);
    }

}
