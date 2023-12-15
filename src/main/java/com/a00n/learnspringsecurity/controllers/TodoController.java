package com.a00n.learnspringsecurity.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.a00n.learnspringsecurity.entities.Todo;
import com.a00n.learnspringsecurity.repositories.TodoRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    @PostMapping
    public Todo postMethodName(@RequestBody Todo todo) {
        return todoRepository.save(todo);
    }

}
