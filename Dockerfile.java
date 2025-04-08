FROM maven:3.9.9-eclipse-temurin-24-alpine

# Set the working directory
WORKDIR /app
# Copy the pom.xml and source code
COPY pom.xml .
COPY src ./src

# Run Maven to build the project
RUN mvn clean package -DskipTests

# Run the application
# You should replace the executable name to the correct one
CMD ["java", "-jar", "target/project_one-1.0-SNAPSHOT-shaded.jar"]
