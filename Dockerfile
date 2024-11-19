# Use the official Maven image to build the app
FROM maven:latest AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml file and install dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code and build the application
COPY src ./src
RUN mvn package -DskipTests

# For Java 23
FROM amazoncorretto:23

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the previous stage
COPY --from=build /app/target/blogs-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
