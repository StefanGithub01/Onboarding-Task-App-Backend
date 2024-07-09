package com.example.onboardingappmandris.Controller;

import com.example.onboardingappmandris.DTO.SearchTaskDTO;
import com.example.onboardingappmandris.DTO.TaskDTO;
import com.example.onboardingappmandris.Entity.Status;
import com.example.onboardingappmandris.Entity.Task;
import com.example.onboardingappmandris.Service.SearchTask;
import com.example.onboardingappmandris.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;
    private final SearchTaskMapper searchTaskMapper;

    @Autowired
    public TaskController(TaskService taskService, SearchTaskMapper searchTaskMapper) {
        this.taskService = taskService;
        this.searchTaskMapper = searchTaskMapper;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskDTO taskDTO) {
        Task createdTask = taskService.createTask(taskDTO);
        return ResponseEntity.ok(createdTask);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Task> updateTaskById(@PathVariable Long id, @RequestBody Task taskModified) {
        Task updatedTask = taskService.updateTask(id, taskModified);
        return ResponseEntity.ok(updatedTask);
    }

    @GetMapping()
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Task>> getTasks(@PathVariable Long userId) {
        return ResponseEntity.ok(taskService.getTasksOfUserId(userId));
    }

    @GetMapping("/statuses")
    public ResponseEntity<Status[]> getStatuses() {
        return ResponseEntity.ok(taskService.getStatues());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Task>> searchTasks(SearchTaskDTO searchTaskDTO){
        List<Task> tasks = taskService.searchTasks(searchTaskMapper.mapToSearchTask(searchTaskDTO));
        return ResponseEntity.ok(tasks);
    }



}
