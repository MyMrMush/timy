# Use the Temurin 17 as the base image
FROM amazoncorretto:17

# Set the working directory
WORKDIR /app

# Copy the Maven wrapper files
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Modify the permissions of the mvnw file
RUN chmod +x ./mvnw

# Copy the module POM files
COPY abstraction/ ./abstraction/
COPY domain/ ./domain/
COPY application/ ./application/
COPY plugins/ ./plugins/
COPY adapters/ ./adapters/

# Build the project
RUN ./mvnw clean install

# Set the start command
CMD ["./mvnw", "-pl", "plugins", "spring-boot:run"]