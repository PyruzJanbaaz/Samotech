package com.pyruz.samotech.core.controller;

import com.pyruz.samotech.core.service.task.TaskService;
import com.pyruz.samotech.shared.model.domain.task.NewTask;
import com.pyruz.samotech.shared.model.domain.task.UpdateTask;
import com.pyruz.samotech.shared.model.dto.base.BaseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class TaskController {

    final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/v1/task")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseDTO addTask(@Valid @RequestBody NewTask newTask) {
        return taskService.addTask(newTask);
    }

    @PutMapping("/v1/task")
    @ResponseStatus(HttpStatus.OK)
    public BaseDTO editTask(@Valid @RequestBody UpdateTask updateTask) {
        return taskService.editTask(updateTask);
    }

    @GetMapping("/v1/task/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseDTO getTask(@PathVariable Integer taskId) {
        return taskService.getTask(taskId);
    }

    @GetMapping("/v1/tasks")
    @ResponseStatus(HttpStatus.OK)
    public BaseDTO getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/v1/tasks/{page}")
    @ResponseStatus(HttpStatus.OK)
    public BaseDTO getTasks(@PathVariable Integer page) {
        return taskService.getTasks(page);
    }

    @DeleteMapping("/v1/task")
    @ResponseStatus(HttpStatus.OK)
    public BaseDTO deleteTask(@RequestParam Integer taskId) {
        return taskService.deleteTask(taskId);
    }

}
