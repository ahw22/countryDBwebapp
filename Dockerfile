# Start from a lightweight Java image
FROM openjdk:26-slim-bullseye

# Create a directory inside the container
WORKDIR /app

# Copy the jar file
COPY target/countryDB-0.0.1-SNAPSHOT.jar app.jar

# Run the jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
