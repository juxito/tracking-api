package com.tracking.backend.controller;

import com.tracking.backend.dto.ApiResponse;
import com.tracking.backend.model.User;
import com.tracking.backend.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping
  public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
    List<User> users = userRepository.findAll();
    return ResponseEntity.ok(ApiResponse.ok("Usuarios obtenidos", users));
  }

  @PostMapping
  public ResponseEntity<ApiResponse<User>> createUser(@Valid @RequestBody User user) {
    // 👆 @Valid activa las anotaciones del modelo
    User saved = userRepository.save(user);
    return ResponseEntity
        .status(HttpStatus.CREATED) // 201
        .body(ApiResponse.ok("Usuario creado correctamente", saved));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
    userRepository.deleteById(id);
    return ResponseEntity.ok(ApiResponse.ok("Usuario eliminado", null));
  }
}