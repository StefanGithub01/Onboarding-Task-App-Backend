package com.example.onboardingappmandris.Service;

import com.example.onboardingappmandris.Entity.Status;
import com.example.onboardingappmandris.Entity.Task;
import com.example.onboardingappmandris.Exceptions.NoTasksFoundException;
import com.example.onboardingappmandris.Exceptions.UserHasNoTasksAssignedException;
import com.example.onboardingappmandris.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;

    }
    public List<Task> getAllTasks() {
        Optional<List<Task>> optionalTasks = Optional.of(taskRepository.findAll());
        return optionalTasks.orElseThrow(() -> new NoTasksFoundException("No tasks found"));
    }

    public List<Task> getTasksOfUserId(Long userId) {
        return taskRepository.findByAssignedId(userId).orElseThrow(() -> new UserHasNoTasksAssignedException("No tasks assigned to user"));
    }

    public Status[] getStatues() {
        return Status.values();
    }


}
