package com.example.onboardingappmandris.Service;

import com.example.onboardingappmandris.DTO.TaskDTO;
import com.example.onboardingappmandris.Entity.Status;
import com.example.onboardingappmandris.Entity.Task;
import com.example.onboardingappmandris.Entity.User;
import com.example.onboardingappmandris.Exceptions.NoTasksFoundException;
import com.example.onboardingappmandris.Exceptions.UserHasNoTasksAssignedException;
import com.example.onboardingappmandris.Repository.TaskRepository;
import com.example.onboardingappmandris.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;

    }

    public Task createTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setSubject(taskDTO.getSubject());
        LocalDate localDate = LocalDate.parse(taskDTO.getDueDate(), DateTimeFormatter.ISO_DATE);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        task.setDueDate(date);
        task.setStatus(Status.valueOf(taskDTO.getStatus()));
        if (taskDTO.getAssignedUserName() != null) {
            User user = userRepository.findByUsername(taskDTO.getAssignedUserName());
            task.setAssigned(user);
        }
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task updatedTask) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new NoTasksFoundException("Task not found"));

        task.setSubject(updatedTask.getSubject());
        task.setStatus(updatedTask.getStatus());
        task.setDueDate(updatedTask.getDueDate());
        task.setAssigned(updatedTask.getAssigned());

        return taskRepository.save(task);
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

    public List<Task> searchTasks(SearchTask searchTask) {
        return taskRepository.searchTasks(searchTask.getUserId(), searchTask.getSubject(), searchTask.getDueDateAfter(), searchTask.getStatus());
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new NoTasksFoundException("No task found"));
    }
}
