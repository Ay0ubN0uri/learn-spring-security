package com.a00n.learnspringsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.a00n.learnspringsecurity.entities.Todo;
import com.a00n.learnspringsecurity.repositories.TodoRepository;

@SpringBootApplication
public class LearnSpringSecurityApplication {

	@Autowired
	private TodoRepository todoRepository;

	public static void main(String[] args) {
		SpringApplication.run(LearnSpringSecurityApplication.class, args);
	}

	@Bean
	CommandLineRunner init() {
		return args -> {
			todoRepository.save(Todo.builder().name("Todo 1").build());
			todoRepository.save(Todo.builder().name("Todo 2").build());
		};
	}

}
