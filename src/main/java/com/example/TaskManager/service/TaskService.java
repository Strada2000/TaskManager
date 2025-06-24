package com.example.TaskManager.service;

import org.springframework.stereotype.Service;
import com.example.TaskManager.repository.TaskRepository;
import com.example.TaskManager.model.Task;
import lombok.RequiredArgsConstructor;
import java.util.Optional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    
    private final TaskRepository taskRepository;

    public Task createTask(Task task){
        return taskRepository.save(task);
    }

    public Optional<Task> getTaskById(Long id){
        return taskRepository.findById(id);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task updateTask(long id, Task taskDetails) {
        Task task = taskRepository.findById(id).orElseThrow();
        
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setIsCompleted(taskDetails.getIsCompleted());
        
        return taskRepository.save(task);
    }

    public void deleteTask(long id){
        taskRepository.deleteById(id);
    }

}
