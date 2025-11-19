# --- STAGE 1: BUILD (Compilación) ---
# Usamos una imagen con Maven y Java 21 oficial
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app

# 1. Copiamos SOLO el pom.xml primero
# Esto permite que Docker guarde en caché las dependencias.
COPY pom.xml .
RUN mvn dependency:go-offline

# 2. Copiamos el código fuente y compilamos
COPY src ./src
# -DskipTests para agilizar la creación de la imagen
RUN mvn clean package -DskipTests

# --- STAGE 2: RUNTIME (Ejecución) ---
# Usamos una imagen ligera (Alpine) solo con JRE (sin Maven ni JDK completo)
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copiamos solo el JAR compilado desde la etapa anterior (builder)
# El *.jar coge cualquier versión, y lo renombramos a app.jar para simplificar
COPY --from=builder /app/target/*.jar app.jar

# Exponemos el puerto 8080
EXPOSE 8080

# Comando de arranque
ENTRYPOINT ["java", "-jar", "app.jar"]