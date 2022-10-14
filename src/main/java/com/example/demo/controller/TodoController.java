package com.example.demo.controller;

import com.example.demo.model.TodoItem;
import com.example.demo.repository.TodoRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.aspectj.apache.bcel.Repository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // build update -ToDoItem- REST API
    @PutMapping("/todo/{id}")
    ResponseEntity<TodoItem> updateToDoItemById(@PathVariable long id, @RequestBody TodoItem item) {
        //get the ToDoItem schema from the repo
        TodoItem updateItem = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item does not exist with id: " + id));

        //update each field of the ToDoItem
        updateItem.setTitle(item.getTitle());
        updateItem.setDescription(item.getDescription());
        updateItem.setCompleted(item.getCompleted());
        updateItem.setPriority(item.getPriority());

        repository.save(updateItem);

        return ResponseEntity.ok(updateItem);
    }
    //Delete a ToDoItem by id
    @DeleteMapping("/todo/{id}")
    ResponseEntity<TodoItem> DeleteToDoItemById(@PathVariable long id) {
        //get the ToDoItem from the repo
        TodoItem updateItem = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item does not exist with id: " + id));

        if (updateItem.getId().equals(id) ) {
            repository.deleteById(id);
            return new ResponseEntity<>(updateItem, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/todo")
    ResponseEntity<?> addNewTodo(@RequestBody TodoItem item){
        return new ResponseEntity<>(repository.save(item), HttpStatus.CREATED);
    }
}
