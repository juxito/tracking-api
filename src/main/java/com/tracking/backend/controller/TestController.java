package com.tracking.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Le avisa a Spring Boot que esta clase manejará rutas de nuestra API
public class TestController {

  @GetMapping("/api/prueba") // Esta será la URL de acceso
  public String saludar() {
    return "¡Profesor, el backend está vivo! Java 22, Spring Boot y XAMPP están conectados correctamente.";
  }
}