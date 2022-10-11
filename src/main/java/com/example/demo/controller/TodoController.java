package com.example.demo.controller;

import com.example.demo.model.TodoItem;
import com.example.demo.repository.TodoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
class TodoController {
    private final TodoRepository repository;

    public TodoController(TodoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/todos")
    ResponseEntity<List<TodoItem>> getTodoList(@RequestParam(value = "sortBy", required = false) String sortBy) {
        if(sortBy != null)
            return new ResponseEntity<>(repository.findAll(Sort.by(Sort.Direction.ASC, sortBy)), HttpStatus.OK);
        else
            return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/todo/{id}")
    ResponseEntity<TodoItem> getTodoById(@PathVariable Long id){
        return new ResponseEntity<>(repository.findById(id).orElseThrow(()-> new RuntimeException("No item for provided id.")), HttpStatus.OK);
    }

    @PostMapping("/todo")
    ResponseEntity<?> addNewTodo(@RequestBody TodoItem item){
        return new ResponseEntity<>(repository.save(item), HttpStatus.CREATED);
    }
}
