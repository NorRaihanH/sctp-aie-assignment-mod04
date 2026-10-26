package com.taskflow.taskflow_api.repository;

import com.taskflow.taskflow_api.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

//@Repository //Optional because TaskRepository extends JpaRepository:
public interface TaskRepository extends JpaRepository<Task, Long> {

}

