package com.tracking.backend.repository;

import com.tracking.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// JpaRepository ya incluye métodos como save(), findAll(), findById(), deleteById()
public interface UserRepository extends JpaRepository<User, Long> {
}