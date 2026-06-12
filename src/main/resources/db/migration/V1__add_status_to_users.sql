-- Agregar columna status con valor por default para registros existentes
ALTER TABLE users 
ADD COLUMN status VARCHAR(20) NOT NULL DEFAULT 1;