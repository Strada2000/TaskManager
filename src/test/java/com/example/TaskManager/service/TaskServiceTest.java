package com.example.TaskManager.service;

import com.example.TaskManager.model.Task;
import com.example.TaskManager.repository.TaskRepository;
import com.example.TaskManager.model.TaskStatus;
import com.example.TaskManager.model.TaskPriority;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    public TaskServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTaskByIdReturnsCorrectTask() {
        Task mockTask = new Task();
        mockTask.setId(1L); // Set the ID to match the findById argument
        mockTask.setTitle("Test Task");
        mockTask.setDescription("This is a test task.");
        mockTask.setIsCompleted(false);
        mockTask.setPriority(TaskPriority.HIGH);
        mockTask.setStatus(TaskStatus.PENDING);
        mockTask.setDueDate(LocalDateTime.now().plusDays(5));

        when(taskRepository.findById(1L)).thenReturn(Optional.of(mockTask));

        Optional<Task> result = taskService.getTaskById(1L);

        assertThat(result).isPresent();
        assertThat(result.get().getTitle()).isEqualTo("Test Task");
        verify(taskRepository, times(1)).findById(1L);
        System.out.println("This is working fine");
    }
}