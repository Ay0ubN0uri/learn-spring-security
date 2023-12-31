package com.a00n.learnspringsecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a00n.learnspringsecurity.entities.Todo;
import com.a00n.learnspringsecurity.entities.User;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findByUser(User user);
}
