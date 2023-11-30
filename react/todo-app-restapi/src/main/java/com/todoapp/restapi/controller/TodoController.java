package com.todoapp.restapi.controller;

import com.todoapp.restapi.model.Todo;
import com.todoapp.restapi.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {

    private final Logger logger = LoggerFactory.getLogger("TodoController");


    @Autowired
    private TodoService todoService;

    @GetMapping("/users/{username}/todos")
    public List<Todo> retrieveTodos(@PathVariable String username) {
        logger.info("retrieveTodos");
        return todoService.findByUsername(username);
    }

    @GetMapping("/users/{username}/todos/{id}")
    public Todo retrieveTodo(@PathVariable String username, @PathVariable int id) {
        logger.info("retrieveTodo by username & id");
        return todoService.findById(id);
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable int id) {
        logger.info("deleteTodo by username & id");
        todoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{username}/todos/{id}")
    public Todo updateTodo(@PathVariable String username, @PathVariable int id, @RequestBody Todo todo) {
        logger.info("updateTodo by username & id");
        todoService.updateTodo(todo);
        return todo;
    }

    @PostMapping("/users/{username}/todos")
    public Todo saveTodo(@PathVariable String username, @RequestBody Todo todo) {
        logger.info("saveTodo by username");
        return todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), todo.isDone());
    }

}
