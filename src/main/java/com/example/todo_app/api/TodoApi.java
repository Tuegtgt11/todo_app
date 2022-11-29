package com.example.todo_app.api;

import com.example.todo_app.entity.Todo;
import com.example.todo_app.entity.myenum.Status;
import com.example.todo_app.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/todo")
@CrossOrigin("*")
public class TodoApi {
    @Autowired
    TodoService todoService;

    @RequestMapping( method = RequestMethod.GET)
    public Page<Todo> findAll(@RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "limit", defaultValue = "10") int limit) {
        return todoService.findAll(page, limit);
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        todo.setCreateAt(LocalDateTime.now());
        todo.setUpdateAt(LocalDateTime.now());
        todo.setStatus(Status.TODO);
        return ResponseEntity.ok(todoService.save(todo));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getTodoById(@PathVariable Long id) {
        Optional<Todo> optionalTodo = todoService.findById(id);
        if (optionalTodo.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalTodo.get());
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public ResponseEntity<Todo> update(@PathVariable Long id, @RequestBody Todo todo) {
        Optional<Todo> optionalTodo = todoService.findById(id);
        if (optionalTodo.isPresent()) {
            ResponseEntity.badRequest().build();
        }
         Todo existTodo =optionalTodo.get();
        existTodo.setStatus(todo.getStatus());
        existTodo.setName(todo.getName());
        existTodo.setDetail(todo.getDetail());
        existTodo.setUpdateAt(LocalDateTime.now());
        return ResponseEntity.ok(todoService.save(existTodo));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/deleted/{id}")
    public ResponseEntity<Todo> delete(@PathVariable Long id) {
        Optional<Todo> optionalTodo = todoService.findById(id);
        if (optionalTodo.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        Todo existTodo =optionalTodo.get();
        existTodo.setStatus(Status.DELETED);
        return ResponseEntity.ok(todoService.save(existTodo));
    }

}
