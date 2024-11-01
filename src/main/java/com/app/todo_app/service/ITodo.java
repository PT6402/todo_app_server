package com.app.todo_app.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.app.todo_app.model.Todo;

public interface ITodo {
  ResponseEntity<List<Todo>> getAll();

  ResponseEntity<?> getOne(int id);

  ResponseEntity<?> add(String title);

  ResponseEntity<?> remove(int id);

  ResponseEntity<?> update(int id, Todo todo);

  ResponseEntity<?> clearAll();

}
