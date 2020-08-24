package com.pyruz.samotech.core.controller;

import com.pyruz.samotech.core.service.task.TaskService;
import com.pyruz.samotech.shared.model.domain.task.NewTask;
import com.pyruz.samotech.shared.model.domain.task.UpdateTask;
import com.pyruz.samotech.shared.model.dto.base.BaseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<BaseDTO> addTask(@Valid @RequestBody NewTask newTask) {
        return new ResponseEntity<>(taskService.addTask(newTask), HttpStatus.CREATED);
    }

    @PutMapping("/v1/task")
    public ResponseEntity<BaseDTO> editTask(@Valid @RequestBody UpdateTask updateTask) {
        return new ResponseEntity<>(taskService.editTask(updateTask), HttpStatus.OK);
    }

    @GetMapping("/v1/task/{taskId}")
    public ResponseEntity<BaseDTO> getTask(@PathVariable Integer taskId) {
        return new ResponseEntity<>(taskService.getTask(taskId), HttpStatus.OK);
    }

    @GetMapping("/v1/tasks")
    public ResponseEntity<BaseDTO> getAllTasks() {
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    @GetMapping("/v1/tasks/{page}")
    public ResponseEntity<BaseDTO> getTasks(@PathVariable Integer page) {
        return new ResponseEntity<>(taskService.getTasks(page), HttpStatus.OK);
    }

    @DeleteMapping("/v1/task")
    public ResponseEntity<BaseDTO> deleteTask(@RequestParam Integer taskId) {
        return new ResponseEntity<>(taskService.deleteTask(taskId), HttpStatus.OK);
    }

}
