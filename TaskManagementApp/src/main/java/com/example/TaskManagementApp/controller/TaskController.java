package com.example.TaskManagementApp.controller;

import com.example.TaskManagementApp.model.Task;
import com.example.TaskManagementApp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks(Principal principal) {
        return taskService.getTasksForUser(principal.getName());
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id, Principal principal) {
        return taskService.getTaskById(id, principal.getName());
    }

    @PostMapping
    public Task createTask(@RequestBody Task task, Principal principal) {
        return taskService.createTaskForUser(task, principal.getName());
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task, Principal principal) {
        return taskService.updateTaskForUser(id, task, principal.getName());
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id, Principal principal) {
        taskService.deleteTaskForUser(id, principal.getName());
    }

}
