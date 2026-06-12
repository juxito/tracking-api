package com.tracking.backend.dto;

import lombok.Data;

@Data
public class ApiResponse<T> {
  private boolean success;
  private String message;
  private T data;

  // ✅ respuesta exitosa con datos
  public static <T> ApiResponse<T> ok(String message, T data) {
    ApiResponse<T> r = new ApiResponse<>();
    r.success = true;
    r.message = message;
    r.data = data;
    return r;
  }

  // ❌ respuesta de error sin datos
  public static <T> ApiResponse<T> error(String message) {
    ApiResponse<T> r = new ApiResponse<>();
    r.success = false;
    r.message = message;
    r.data = null;
    return r;
  }
}