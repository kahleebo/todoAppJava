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

// Add a PutMapping to update an item by posting the updated object to its REST endpoint
    @PutMapping("/todo/{id}")
    public ResponseEntity<TodoItem> updateItem(@PathVariable long id, @RequestBody TodoItem todo) {
        TodoItem updateItem = repository.findById(id)
                .orElseThrow(() -> new IllegalStateException("item not exist with id: " + id));

        updateItem.setPriority(todo.getPriority());
     updateItem.setTitle(todo.getTitle());
        updateItem.setDescription(todo.getDescription());
        updateItem.setCompleted(todo.getCompleted());

        return new ResponseEntity<>(repository.save(updateItem), HttpStatus.OK);
    }

    @PostMapping("/todo")
    ResponseEntity<?> addNewTodo(@RequestBody TodoItem item){
        return new ResponseEntity<>(repository.save(item), HttpStatus.CREATED);
    }

    @DeleteMapping("/todo/{id}")
    public ResponseEntity<TodoItem> deleteToDo(@PathVariable long id, @RequestBody TodoItem todo) {
        TodoItem removeItem = repository.findById(id)
        .orElseThrow(() -> new IllegalStateException("item not exist with id: " + id));

        repository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);


    }
}

