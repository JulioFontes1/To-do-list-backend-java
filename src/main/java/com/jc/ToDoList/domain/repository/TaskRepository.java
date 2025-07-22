package com.jc.ToDoList.domain.repository;

import com.jc.ToDoList.domain.model.TaskStatus;
import com.jc.ToDoList.domain.model.TasksModel;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<TasksModel, Long> {

    Optional<TasksModel> findById(Long id);
    TasksModel findByTitle(String title);
    List<TasksModel> findByStatus(TaskStatus status);
}
