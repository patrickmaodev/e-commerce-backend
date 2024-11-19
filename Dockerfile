FROM openjdk:23-jdk-slim

# Install Maven
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

# Set the working directory
WORKDIR /app

# Copy Maven wrapper and project files
COPY .mvn/ .mvn/
COPY mvnw .
RUN chmod +x mvnw
COPY pom.xml .

# Download dependencies
RUN ./mvnw clean dependency:resolve

# Copy the application source code
COPY src ./src

# Run Maven to build and run the application
CMD ["./mvnw", "spring-boot:run"]
