package com.pyruz.samotech.core.service.project;

import com.pyruz.samotech.core.model.entity.Collections;
import com.pyruz.samotech.core.model.entity.Project;
import com.pyruz.samotech.core.repository.CollectionsRepository;
import com.pyruz.samotech.core.repository.ProjectRepository;
import com.pyruz.samotech.shared.handler.exception.ServiceException;
import com.pyruz.samotech.shared.model.domain.project.NewProject;
import com.pyruz.samotech.shared.model.domain.project.UpdateProject;
import com.pyruz.samotech.shared.model.dto.base.BaseDTO;
import com.pyruz.samotech.shared.model.dto.base.MetaDTO;
import com.pyruz.samotech.shared.model.dto.base.PagerDTO;
import com.pyruz.samotech.shared.utility.ApplicationProperties;
import com.pyruz.samotech.shared.utility.ApplicationUtilities;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    final ApplicationProperties applicationProperties;
    final ProjectRepository projectRepository;
    final CollectionsRepository collectionsRepository;

    public ProjectService(ApplicationProperties applicationProperties, ProjectRepository projectRepository, CollectionsRepository collectionsRepository) {
        this.applicationProperties = applicationProperties;
        this.projectRepository = projectRepository;
        this.collectionsRepository = collectionsRepository;
    }

    public BaseDTO addProject(NewProject newProject) {
        Project project = new Project();
        project.setTitle(newProject.getTitle());
        project.setCaption(newProject.getCaption());
        project.setBackgroundColor(newProject.getBackgroundColor());
        project.setBackgroundImage(newProject.getBackgroundImage());

        Collections collection = collectionsRepository.findById(newProject.getCollectionId()).orElseThrow(
                () -> new ServiceException(
                        applicationProperties.getCode("not-found-code"),
                        applicationProperties.getProperty("not-found-text"),
                        HttpStatus.NOT_FOUND
                )
        );
        project.setCollection(collection);
        projectRepository.save(project);
        return BaseDTO.builder().meta(MetaDTO.getInstance(applicationProperties)).build();
    }


    public BaseDTO editProject(UpdateProject updateProject) {
        Project project = projectRepository.findById(updateProject.getId()).orElseThrow(
                () -> new ServiceException(
                        applicationProperties.getCode("not-found-code"),
                        applicationProperties.getProperty("not-found-text"),
                        HttpStatus.NOT_FOUND
                )
        );
        project.setTitle(updateProject.getTitle());
        project.setCaption(updateProject.getCaption());
        project.setBackgroundColor(updateProject.getBackgroundColor());
        project.setBackgroundImage(updateProject.getBackgroundImage());
        //project.setEpics(epicRepository.findEpicByIdIn(updateProject.getEpics()));
        projectRepository.save(project);
        return BaseDTO.builder().meta(MetaDTO.getInstance(applicationProperties)).build();
    }


    public BaseDTO<Project> getProject(Integer id) {
        return new BaseDTO<>(MetaDTO.getInstance(applicationProperties), projectRepository.findById(id).orElseThrow(
                () -> new ServiceException(
                        applicationProperties.getCode("not-found-code"),
                        applicationProperties.getProperty("not-found-text"),
                        HttpStatus.NOT_FOUND
                )
        ));
    }


    public BaseDTO<List<Project>> getProjectByCollectionId(Integer collectionId) {
        return new BaseDTO<>(MetaDTO.getInstance(applicationProperties), projectRepository.findProjectByCollectionId(collectionId));
    }


    public BaseDTO deleteProject(Integer id) {
        Project project = projectRepository.findById(id).orElseThrow(
                () -> new ServiceException(
                        applicationProperties.getCode("not-found-code"),
                        applicationProperties.getProperty("not-found-text"),
                        HttpStatus.NOT_FOUND
                )
        );
        project.setIsDeleted(true);
        projectRepository.save(project);
        return BaseDTO.builder().meta(MetaDTO.getInstance(applicationProperties)).build();
    }


    public BaseDTO<java.util.List<Project>> getAllProjects() {
        return new BaseDTO<>(MetaDTO.getInstance(applicationProperties), projectRepository.findAll());
    }


    public BaseDTO<PagerDTO<Project>> getProjects(Integer page) {
        Pageable pageable = ApplicationUtilities.getInstance().pageable(page, applicationProperties);
        Page<Project> projects = projectRepository.findAll(pageable);
        PagerDTO<Project> projectPagerDTO = new PagerDTO<>(projects);
        return new BaseDTO<>(MetaDTO.getInstance(applicationProperties), projectPagerDTO);
    }
}
