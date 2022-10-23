package com.example.tonghopdemo.loginAndRegister.repo;

import com.example.tonghopdemo.loginAndRegister.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
