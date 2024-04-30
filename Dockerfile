# Use an official Java runtime as a parent image
FROM openjdk:17-slim as build

# Set the working directory in the container
WORKDIR /app

# Copy the Maven executable to the working directory
COPY mvnw .
COPY .mvn .mvn

# Copy the pom.xml file and install dependencies
COPY pom.xml .
RUN ./mvnw dependency:go-offline

# Copy the rest of your application's source code
COPY src src

# Build the application, skipping tests for build speed
RUN ./mvnw package -DskipTests

# Use OpenJDK for running the application
FROM openjdk:17-slim
COPY --from=build /app/target/*.jar /app/application.jar

# Set the container to run as a non-root user
RUN addgroup --system spring && adduser --system spring --ingroup spring
USER spring:spring

# Expose port 80
EXPOSE 80

# Run the application
ENTRYPOINT ["java", "-jar", "/app/application.jar"]