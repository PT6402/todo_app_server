package com.app.todo_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.todo_app.model.Todo;

@Repository
public interface TodoRepo extends JpaRepository<Todo, Integer> {
  Optional<Todo> findByTitle(String title);
}
