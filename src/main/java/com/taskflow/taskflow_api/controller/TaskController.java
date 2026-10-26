package com.taskflow.taskflow_api.controller;
import com.taskflow.taskflow_api.entity.Task;
import com.taskflow.taskflow_api.service.TaskService;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    //@Autowired //optional because there is only one constructor
    private TaskService taskService;
    public TaskController(TaskService taskService) { // Constructor injection
        this.taskService = taskService;
    }   
    
    @GetMapping("")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.findAllTasks();
        return ResponseEntity.ok(tasks);
    }
    
    // TODO: Complete this method
    // Call taskService.findTaskById(id)
    // Return ResponseEntity.ok(task)
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        // YOUR CODE HERE
        //return null;
        Task task = null;
        try{
            task = taskService.findTaskById(id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }
    
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }
}

