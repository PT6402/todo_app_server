package com.app.todo_app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.todo_app.model.Todo;
import com.app.todo_app.service.ITodo;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TodoController {
  private final ITodo todo_service;

  @GetMapping
  public ResponseEntity<List<Todo>> getAll() {
    return todo_service.getAll();
  }

  @GetMapping("{id}")
  public ResponseEntity<?> getOne(@PathVariable int id) {
    return todo_service.getOne(id);
  }

  @PutMapping("{id}")
  public ResponseEntity<?> update(@PathVariable int id, @RequestBody Todo todo) {
    return todo_service.update(id, todo);
  }

  @PostMapping
  public ResponseEntity<?> add(@RequestBody Todo todo) {
    return todo_service.add(todo);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<?> delete(@PathVariable int id) {
    return todo_service.remove(id);
  }

  @PutMapping("completed/{id}")
  public ResponseEntity<?> updateCompleted(@PathVariable int id, @PathParam("isComleted") boolean isCompleted) {
    return todo_service.updateComplete(id, isCompleted);
  }
}
