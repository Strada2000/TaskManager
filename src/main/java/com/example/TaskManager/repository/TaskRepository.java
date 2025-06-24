package com.example.TaskManager.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.TaskManager.model.Task;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
    //Page<Task> findByCompleted(boolean completed, Pageable pageable);
}
