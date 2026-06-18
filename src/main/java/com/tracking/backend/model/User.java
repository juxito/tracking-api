package com.tracking.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // 👈 Autoincrementable listo
  @Column(nullable = false)
  private Integer id;

  @NotBlank(message = "El nombre es obligatorio")
  @Column(name = "full_name", nullable = false)
  private String name;

  @NotBlank(message = "El email es obligatorio")
  @Email(message = "El email no tiene un formato válido") // 👈 Validación en Java
  // Agregamos un CHECK constraint en Postgres para validar el formato de correo directamente en la BD:
  @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(255) CHECK (email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$')")
  private String email;

  @NotNull(message = "El rol es obligatorio") // 👈 Cambiado a @NotNull porque es un Integer, no un String
  @Column(nullable = false)
  private Integer role_id;

  @NotBlank(message = "La contraseña es obligatoria")
  @Column(nullable = false)
  private String password_hash; 

  @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE") // 👈 'true' por defecto en Postgres
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private Boolean is_active = true; // 👈 'true' por defecto en Java para nuevos objetos
}