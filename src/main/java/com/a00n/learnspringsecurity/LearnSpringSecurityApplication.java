package com.a00n.learnspringsecurity;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.a00n.learnspringsecurity.entities.Role;
import com.a00n.learnspringsecurity.entities.Todo;
import com.a00n.learnspringsecurity.entities.User;
import com.a00n.learnspringsecurity.repositories.RoleRepository;
import com.a00n.learnspringsecurity.repositories.TodoRepository;
import com.a00n.learnspringsecurity.repositories.UserRepository;

@SpringBootApplication
public class LearnSpringSecurityApplication {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TodoRepository todoRepository;

	public static void main(String[] args) {
		SpringApplication.run(LearnSpringSecurityApplication.class, args);
	}

	@Bean
	CommandLineRunner init() {
		return args -> {
			Role adminRole = Role.builder().name("ROLE_ADMIN").build();
			Role userRole = Role.builder().name("ROLE_USER").build();
			Role managerRole = Role.builder().name("ROLE_MANAGER").build();
			adminRole = roleRepository.save(adminRole);
			userRole = roleRepository.save(userRole);
			managerRole = roleRepository.save(managerRole);
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			var admin = User.builder()
					.firstName("admin")
					.lastName("admin")
					.username("admin")
					.email("admin@gmail.com")
					.roles(Set.of(adminRole, userRole, managerRole))
					.password(passwordEncoder.encode("admin"))
					.build();

			var user = User.builder()
					.firstName("user")
					.lastName("user")
					.username("user")
					.email("user@gmail.com")
					.roles(Set.of(userRole))
					.password(passwordEncoder.encode("user"))
					.build();
			var manager = User.builder()
					.firstName("manager")
					.lastName("manager")
					.username("manager")
					.email("manager@gmail.com")
					.roles(Set.of(managerRole))
					.password(passwordEncoder.encode("manager"))
					.build();

			userRepository.save(admin);
			userRepository.save(user);
			userRepository.save(manager);
			todoRepository.save(Todo.builder().name("Todo 1").user(admin).build());
			todoRepository.save(Todo.builder().name("Todo 2").user(user).build());
		};
	}

}
