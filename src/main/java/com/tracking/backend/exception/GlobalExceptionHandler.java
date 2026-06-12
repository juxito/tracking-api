package com.tracking.backend.exception;

import com.tracking.backend.dto.ApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice // 👈 intercepta excepciones de TODOS los controllers
public class GlobalExceptionHandler {

  // 🔴 errores de @NotBlank, @Email, etc.
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse<Void>> handleValidation(MethodArgumentNotValidException ex) {
    String errors = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(e -> e.getField() + ": " + e.getDefaultMessage())
        .collect(Collectors.joining(", "));

    return ResponseEntity
        .badRequest() // 400
        .body(ApiResponse.error(errors));
  }

  // 🔴 email duplicado u otras violaciones de constraint en BD
  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ApiResponse<Void>> handleDataIntegrity(DataIntegrityViolationException ex) {
    String msg = "Error de integridad en base de datos";

    // detectar email duplicado específicamente
    if (ex.getMessage() != null && ex.getMessage().contains("email")) {
      msg = "El email ya está registrado";
    }

    return ResponseEntity
        .status(HttpStatus.CONFLICT) // 409
        .body(ApiResponse.error(msg));
  }

  // 🔴 cualquier otro error no controlado
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse<Void>> handleGeneric(Exception ex) {
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR) // 500
        .body(ApiResponse.error("Error interno del servidor"));
  }
}