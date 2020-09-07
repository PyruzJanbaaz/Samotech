package com.pyruz.samotech.core.service.task;

import com.pyruz.samotech.core.model.entity.Task;
import com.pyruz.samotech.core.repository.StateRepository;
import com.pyruz.samotech.core.repository.TaskRepository;
import com.pyruz.samotech.shared.handler.exception.ServiceException;
import com.pyruz.samotech.shared.model.domain.task.NewTask;
import com.pyruz.samotech.shared.model.domain.task.UpdateTask;
import com.pyruz.samotech.shared.model.dto.base.BaseDTO;
import com.pyruz.samotech.shared.model.dto.base.MetaDTO;
import com.pyruz.samotech.shared.model.dto.base.PagerDTO;
import com.pyruz.samotech.shared.utility.ApplicationProperties;
import com.pyruz.samotech.shared.utility.ApplicationUtilities;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TaskService {

    final ApplicationProperties applicationProperties;
    final StateRepository stateRepository;
    final TaskRepository taskRepository;

    public TaskService(ApplicationProperties applicationProperties, StateRepository stateRepository, TaskRepository taskRepository) {
        this.applicationProperties = applicationProperties;
        this.stateRepository = stateRepository;
        this.taskRepository = taskRepository;
    }


    public BaseDTO addTask(NewTask newTask) {
        Task task = new Task();
        task.setTitle(newTask.getTitle());
        task.setStates(stateRepository.findById(newTask.getState()).orElseThrow(
                () -> new ServiceException(
                        applicationProperties.getCode("not-found-code"),
                        applicationProperties.getProperty("not-found-text"),
                        HttpStatus.NOT_FOUND
                )
        ));
        task.setTaskCode(UUID.randomUUID().toString());
        taskRepository.save(task);
        return BaseDTO.builder()
                .meta(MetaDTO.getInstance(applicationProperties))
                .build();
    }


    public BaseDTO editTask(UpdateTask updateTask) {
        Task task = taskRepository.findById(updateTask.getId()).orElseThrow(
                () -> new ServiceException(
                        applicationProperties.getCode("not-found-code"),
                        applicationProperties.getProperty("not-found-text"),
                        HttpStatus.NOT_FOUND
                )
        );
        task.setTitle(updateTask.getTitle());
        task.setStates(stateRepository.findById(updateTask.getState()).orElseThrow(
                () -> new ServiceException(
                        applicationProperties.getCode("not-found-code"),
                        applicationProperties.getProperty("not-found-text"),
                        HttpStatus.NOT_FOUND
                )
        ));
        taskRepository.save(task);
        return BaseDTO.builder()
                .meta(MetaDTO.getInstance(applicationProperties))
                .build();
    }


    public BaseDTO getTask(Integer id) {
        return BaseDTO.builder()
                .meta(MetaDTO.getInstance(applicationProperties))
                .data(
                        taskRepository.findById(id).orElseThrow(
                                () -> new ServiceException(
                                        applicationProperties.getCode("not-found-code"),
                                        applicationProperties.getProperty("not-found-text"),
                                        HttpStatus.NOT_FOUND
                                )
                        )
                ).build();

    }


    public BaseDTO deleteTask(Integer id) {
        Task task = taskRepository.findById(id).orElseThrow(
                () -> new ServiceException(
                        applicationProperties.getCode("not-found-code"),
                        applicationProperties.getProperty("not-found-text"),
                        HttpStatus.NOT_FOUND
                )
        );
        task.setIsDeleted(true);
        taskRepository.save(task);
        return BaseDTO.builder()
                .meta(MetaDTO.getInstance(applicationProperties))
                .build();
    }


    public BaseDTO getAllTasks() {
        return BaseDTO.builder()
                .meta(MetaDTO.getInstance(applicationProperties))
                .data(taskRepository.findAll())
                .build();
    }


    public BaseDTO getTasks(Integer page) {
        Pageable pageable = ApplicationUtilities.getInstance().pageable(page, applicationProperties);
        Page<Task> tasks = taskRepository.findAll(pageable);
        PagerDTO<Task> taskPagerDTO = new PagerDTO<>(tasks);
        return BaseDTO.builder()
                .meta(MetaDTO.getInstance(applicationProperties))
                .data(taskPagerDTO)
                .build();

    }
}
