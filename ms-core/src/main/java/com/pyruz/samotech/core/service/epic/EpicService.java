package com.pyruz.samotech.core.service.epic;

import com.pyruz.samotech.core.model.entity.Epic;
import com.pyruz.samotech.core.repository.EpicRepository;
import com.pyruz.samotech.core.repository.ProjectRepository;
import com.pyruz.samotech.core.repository.StateRepository;
import com.pyruz.samotech.shared.handler.exception.ServiceException;
import com.pyruz.samotech.shared.model.domain.epic.NewEpic;
import com.pyruz.samotech.shared.model.domain.epic.UpdateEpic;
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
import java.util.UUID;

@Service
public class EpicService {

    final ApplicationProperties applicationProperties;
    final StateRepository stateRepository;
    final ProjectRepository projectRepository;
    final EpicRepository epicRepository;

    public EpicService(ApplicationProperties applicationProperties, StateRepository stateRepository, ProjectRepository projectRepository, EpicRepository epicRepository) {
        this.applicationProperties = applicationProperties;
        this.stateRepository = stateRepository;
        this.projectRepository = projectRepository;
        this.epicRepository = epicRepository;
    }


    public BaseDTO<String> addEpic(NewEpic newEpic) {
        Epic epic = new Epic();
        epic.setTitle(newEpic.getTitle());
        epic.setProject(projectRepository.findById(newEpic.getProjectId()).orElseThrow(
                () -> new ServiceException(
                        applicationProperties.getCode("not-found-code"),
                        applicationProperties.getProperty("not-found-text"),
                        HttpStatus.NOT_FOUND
                )
        ));
        epic.setStates(stateRepository.findById(newEpic.getState()).orElseThrow(
                () -> new ServiceException(
                        applicationProperties.getCode("not-found-code"),
                        applicationProperties.getProperty("not-found-text"),
                        HttpStatus.NOT_FOUND
                )
        ));
        epic.setEpicCode(UUID.randomUUID().toString());
        epicRepository.save(epic);
        return new BaseDTO<>(MetaDTO.getInstance(applicationProperties), epic.getEpicCode());
    }


    public BaseDTO editEpic(UpdateEpic updateEpic) {
        Epic epic = epicRepository.findById(updateEpic.getId()).orElseThrow(
                () -> new ServiceException(
                        applicationProperties.getCode("not-found-code"),
                        applicationProperties.getProperty("not-found-text"),
                        HttpStatus.NOT_FOUND
                )
        );
        epic.setTitle(updateEpic.getTitle());
        epic.setStates(stateRepository.findById(updateEpic.getState()).orElseThrow(
                () -> new ServiceException(
                        applicationProperties.getCode("not-found-code"),
                        applicationProperties.getProperty("not-found-text"),
                        HttpStatus.NOT_FOUND
                )
        ));
        epicRepository.save(epic);
        return BaseDTO.builder().meta(MetaDTO.getInstance(applicationProperties)).build();
    }


    public BaseDTO<Epic> getEpic(Integer id) {
        return new BaseDTO<>(MetaDTO.getInstance(applicationProperties), epicRepository.findById(id).orElseThrow(
                () -> new ServiceException(
                        applicationProperties.getCode("not-found-code"),
                        applicationProperties.getProperty("not-found-text"),
                        HttpStatus.NOT_FOUND
                )
        ));

    }


    public BaseDTO deleteEpic(Integer id) {
        Epic epic = epicRepository.findById(id).orElseThrow(
                () -> new ServiceException(
                        applicationProperties.getCode("not-found-code"),
                        applicationProperties.getProperty("not-found-text"),
                        HttpStatus.NOT_FOUND
                )
        );
        epic.setIsDeleted(true);
        epicRepository.save(epic);
        return BaseDTO.builder().meta(MetaDTO.getInstance(applicationProperties)).build();
    }


    public BaseDTO<List<Epic>> getAllEpics() {
        return new BaseDTO<>(MetaDTO.getInstance(applicationProperties), epicRepository.findAll());
    }


    public BaseDTO<PagerDTO<Epic>> getEpics(Integer page) {
        Pageable pageable = ApplicationUtilities.getInstance().pageable(page, applicationProperties);
        Page<Epic> epics = epicRepository.findAll(pageable);
        PagerDTO<Epic> epicPagerDTO = new PagerDTO<>(epics);
        return new BaseDTO<>(MetaDTO.getInstance(applicationProperties), epicPagerDTO);
    }
}
