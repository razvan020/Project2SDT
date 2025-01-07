FROM openjdk:21-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Maven-built JAR file into the container
COPY target/*.jar app.jar

# Expose the application port (update with your microservice's port)
EXPOSE 8081

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]