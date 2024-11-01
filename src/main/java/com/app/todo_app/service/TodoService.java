package com.app.todo_app.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.todo_app.model.Todo;
import com.app.todo_app.repository.TodoRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService implements ITodo {
  private final TodoRepo todoRepo;

  @Override
  public ResponseEntity<List<Todo>> getAll() {
    return ResponseEntity.ok(todoRepo.findAll());
  }

  @Override
  public ResponseEntity<?> getOne(int id) {
    try {
      Todo todoCheck = todoRepo.findById(id).orElseThrow(() -> new Exception("not found"));
      return ResponseEntity.ok(todoCheck);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @Override
  public ResponseEntity<?> add(String title) {
    try {
      if (todoRepo.findByTitle(title).isPresent()) {
        throw new Exception("is already");
      }
      Todo todo = Todo.builder().title(title).isCompleted(false).build();
      Todo savedTodo = todoRepo.save(todo);
      return ResponseEntity.ok(savedTodo);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @Override
  public ResponseEntity<?> remove(int id) {
    try {
      Todo checkTodo = todoRepo.findById(id).orElseThrow(() -> new Exception("not found"));
      todoRepo.deleteById(id);
      return ResponseEntity.ok(checkTodo);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @Override
  public ResponseEntity<?> update(int id, Todo todo) {
    try {
      Todo checkTodo = todoRepo.findById(id).orElseThrow(() -> new Exception("not found"));

      if (todoRepo.findByTitle(todo.getTitle()).isPresent()) {
        if (!todo.getTitle().equals(checkTodo.getTitle())) {
          throw new Exception("is already title");
        }
      }
      if (todo.getTitle() != null) {
        checkTodo.setTitle(todo.getTitle());
      }
      if (todo.getIsCompleted() != null) {
        checkTodo.setIsCompleted(todo.getIsCompleted());
      }
      todoRepo.save(checkTodo);
      return ResponseEntity.ok(checkTodo);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @Override
  public ResponseEntity<?> clearAll() {
    try {
      todoRepo.deleteAll();
      return ResponseEntity.ok("clear all success");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

}
