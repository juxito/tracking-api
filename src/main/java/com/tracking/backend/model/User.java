package com.tracking.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Integer id;

  @NotBlank(message = "El nombre es obligatorio")
  @Column(name = "full_name", nullable = false)
  private String name;

  @NotBlank(message = "El email es obligatorio")
  @Email(message = "El email no tiene un formato válido")
  @Column(nullable = false, unique = true)
  private String email;

  @NotBlank(message = "El rol es obligatorio")
  @Column(nullable = false)
  private Integer role_id;

  @NotBlank(message = "La contraseña es obligatoria")
  @Column(nullable = false)
  private String password_hash; // 👈 por ahora plain text, luego BCrypt

  @Column(nullable = false)
  @JsonProperty(access = JsonProperty.Access.READ_ONLY) // 👈 front puede leerlo, no escribirlo
  private Boolean is_active;
}