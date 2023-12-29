package com.example.demo.todo;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public void addTodo(String username, String description, LocalDate targetDate, Boolean done) {
        Todo todo = new Todo();
        todo.setUsername(username);
        todo.setDescription(description);
        todo.setTargetDate(targetDate);
        todo.setDone(done);
        todoRepository.save(todo);
    }
    public void addTodo(Todo todo) {
        todoRepository.save(todo);
    }

    public void deleteById(int id) {
        todoRepository.deleteById(id);
    }

    public Todo findById(int id) {
        return todoRepository.findById(id).orElse(null);
    }

    public void updateTodo(@Valid Todo todo) {
        todoRepository.save(todo);
    }

    public List<Todo> findByUsername(String username) {
        return todoRepository.findByUsername(username);
    }
}
