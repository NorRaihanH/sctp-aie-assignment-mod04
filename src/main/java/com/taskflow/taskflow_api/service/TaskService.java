package com.taskflow.taskflow_api.service;

import com.taskflow.taskflow_api.entity.Task;
import com.taskflow.taskflow_api.repository.TaskRepository;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {
    
    //Use constructor injection to bring the repository into the service.
    
    //@Autowired //optional as there is only one constructor
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    
    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }
    
    // Use taskRepository.findById(id)
    // If not found, throw new RuntimeException("Task not found with id: " + id)
    public Task findTaskById(Long id) {
        // CODE ADDED HERE
        return taskRepository.findById(id)
                .orElseThrow(
                    () -> new RuntimeException("Task not found with id: " + id)
                );
        
        //return null;
    }
    
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }
}
