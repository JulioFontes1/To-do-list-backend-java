package com.jc.ToDoList.controller;


import com.jc.ToDoList.domain.model.TaskStatus;
import com.jc.ToDoList.domain.model.TasksModel;
import com.jc.ToDoList.services.TaskServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    private final TaskServices taskServices;

    public TasksController(TaskServices taskServices) {
        this.taskServices = taskServices;
    }

    @GetMapping("/")
    public ResponseEntity<List<TasksModel>> findAll(){
        var tasks = taskServices.findAll();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<TasksModel> findByTitle(@PathVariable String title){
        var task = taskServices.findByTitle(title);
        return ResponseEntity.ok(task);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<TasksModel>> findByStatus(@PathVariable TaskStatus status){
        var tasks = taskServices.findByStatus(status);
        return ResponseEntity.ok(tasks);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody TasksModel task){
        taskServices.turnTodoTask(task);
        taskServices.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @PostMapping("/task/todo/{id}")
    public ResponseEntity<TasksModel> turnTodo(@PathVariable Long id) {
        TasksModel task = taskServices.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        taskServices.createTask(task);
        return ResponseEntity.ok(task);
    }

    @PostMapping("/task/doing/{id}")
    public ResponseEntity<TasksModel> turnDoing(@PathVariable Long id) {
        TasksModel task = taskServices.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        taskServices.turnDoingTask(task);

        taskServices.createTask(task);
        return ResponseEntity.ok(task);
    }

    @PostMapping("/task/finished/{id}")
    public ResponseEntity<TasksModel> turnFinished(@PathVariable Long id) {
        TasksModel task = taskServices.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        taskServices.turnFinishedTask(task);

        taskServices.createTask(task);
        return ResponseEntity.ok(task);

    }
}
