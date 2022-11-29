package com.example.todo_app.service;

import com.example.todo_app.entity.Todo;
import com.example.todo_app.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {
    @Autowired
    TodoRepository todoRepository;

    public Page<Todo> findAll(int page,  int limit){
        return todoRepository.findAllBy( PageRequest.of(page,limit));
    }

    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    public Optional<Todo> findById(Long id){
        return  todoRepository.findById(id);
    }

}
