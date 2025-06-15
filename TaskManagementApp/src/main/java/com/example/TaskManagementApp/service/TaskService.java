package com.example.TaskManagementApp.service;

import com.example.TaskManagementApp.model.Task;
import com.example.TaskManagementApp.model.User;
import com.example.TaskManagementApp.repository.TaskRepository;
import com.example.TaskManagementApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Task> getTasksForUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        return taskRepository.findByUser(user);
    }

    public Task createTaskForUser(Task task, String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        task.setUser(user);
        return taskRepository.save(task);
    }

    public Task updateTaskForUser(Long id, Task updatedTask, String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        Task task = taskRepository.findById(id).orElseThrow();
        if (!task.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        return taskRepository.save(task);
    }

    public void deleteTaskForUser(Long id, String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        Task task = taskRepository.findById(id).orElseThrow();
        if (!task.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }
        taskRepository.delete(task);
    }

    public Task getTaskById(Long id, String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        Task task = taskRepository.findById(id).orElseThrow();
        if (!task.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }
        return task;
    }

}
