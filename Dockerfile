# Usa una imagen base con Java
FROM eclipse-temurin:17-jdk

# Directorio de trabajo
WORKDIR /app

# Copia todo el proyecto
COPY . .

# Dar permisos al wrapper
RUN chmod +x mvnw

# Construir el proyecto
# Construye el jar
RUN ./mvnw clean package -DskipTests

# Expone el puerto
EXPOSE 8080

# Ejecuta la app
CMD ["sh", "-c", "java -jar target/*.jar"]