package com.jc.ToDoList.services;

import com.jc.ToDoList.domain.model.TaskStatus;
import com.jc.ToDoList.domain.model.TasksModel;

import java.util.List;
import java.util.Optional;

public interface TaskServices {
    TasksModel createTask(TasksModel taskToCreate);
    TasksModel findByTitle(String title);
    Optional<TasksModel> findById(Long id);
    List<TasksModel> findAll();
    List<TasksModel> findByStatus(TaskStatus status);
    void turnTodoTask(TasksModel task);
    void turnDoingTask(TasksModel task);
    void turnFinishedTask(TasksModel task);


}
