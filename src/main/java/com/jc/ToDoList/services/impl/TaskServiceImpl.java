package com.jc.ToDoList.services.impl;

import com.jc.ToDoList.domain.model.TaskStatus;
import com.jc.ToDoList.domain.model.TasksModel;
import com.jc.ToDoList.domain.repository.TaskRepository;
import com.jc.ToDoList.services.TaskServices;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Service
public class TaskServiceImpl implements TaskServices {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TasksModel createTask(TasksModel taskToCreate) {
        taskToCreate.setRegisterDate(LocalDate.now());
        taskRepository.save(taskToCreate);
        return taskToCreate;
    }

    @Override
    public TasksModel findByTitle(String title) {

        return taskRepository.findByTitle(title);
    }

    @Override
    public List<TasksModel> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public List<TasksModel> findByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    @Override
    public void turnTodoTask(TasksModel task) {
        task.setStatus("TODO");
        taskRepository.save(task);
    }

    @Override
    public void turnDoingTask(TasksModel task) {
        task.setStatus("DOING");
        taskRepository.save(task);
    }

    @Override
    public void turnFinishedTask(TasksModel task) {
        task.setStatus("FINISHED");
        task.setFinishDate(LocalDate.now());
        taskRepository.save(task);

    }

    @Override
    public Optional<TasksModel> findById(Long id) {
        return taskRepository.findById(id);
    }
}
