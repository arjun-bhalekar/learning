package com.todoapp.restapi.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.todoapp.restapi.model.Todo;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private static List<Todo> todos = new ArrayList<>();

    private static int todosCount = 0;

    static {
        todos.add(new Todo(++todosCount, "arjun", "Get AWS Certified",
                LocalDate.now().plusYears(10), false));
        todos.add(new Todo(++todosCount, "arjun", "Learn DevOps",
                LocalDate.now().plusYears(11), false));
        todos.add(new Todo(++todosCount, "ranga", "Learn Full Stack Development",
                LocalDate.now().plusYears(12), false));
        todos.add(new Todo(++todosCount, "arjun", "Read Effective Java Book",
                LocalDate.now().plusYears(5), false));
        todos.add(new Todo(++todosCount, "arjun", "Buy iPhone 13",
                LocalDate.now().plusYears(6), false));
        todos.add(new Todo(++todosCount, "arjun", "Buy Dell HD Monitor",
                LocalDate.now(), true));
    }

    public List<Todo> findByUsername(String username) {
        return todos.stream().filter(todo -> todo.getUsername().equalsIgnoreCase(username)).collect(Collectors.toList());
    }

    public Todo addTodo(String username, String description, LocalDate targetDate, boolean done) {
        Todo todo = new Todo(++todosCount, username, description, targetDate, done);
        todos.add(todo);
        return todo;
    }

    public void deleteById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        todos.removeIf(predicate);
    }

    public Todo findById(int id) {
        Todo todo = todos.stream().filter(t -> t.getId() == id).findFirst().get();
        return todo;
    }

    public void updateTodo(Todo todo) {
        deleteById(todo.getId());
        todos.add(todo);
    }
}