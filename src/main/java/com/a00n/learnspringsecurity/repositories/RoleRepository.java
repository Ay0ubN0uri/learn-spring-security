package com.a00n.learnspringsecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a00n.learnspringsecurity.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
