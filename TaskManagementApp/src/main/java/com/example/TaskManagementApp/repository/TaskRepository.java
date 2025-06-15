package com.example.TaskManagementApp.repository;

import com.example.TaskManagementApp.model.Task;
import com.example.TaskManagementApp.model.User;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Registered
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
}
