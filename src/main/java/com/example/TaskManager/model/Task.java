package com.example.TaskManager.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long id;

     private String title;

     private String description;

     private Boolean isCompleted;

     @Enumerated(EnumType.STRING)
     private TaskPriority priority;

     @Enumerated(EnumType.STRING)
     private TaskStatus status;

     @CreationTimestamp
     private LocalDateTime createdAt;
     
     private LocalDateTime dueDate;


}
