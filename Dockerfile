FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copy the project files
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Environment variables with defaults
ENV DB_HOST=mysql \
    DB_PORT=3306 \
    DB_NAME=ipo_db \
    DB_USERNAME=root \
    DB_PASSWORD=password \
    SPRING_PROFILES_ACTIVE=prod

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"] 