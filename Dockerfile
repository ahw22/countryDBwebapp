# Start from a lightweight Java image
FROM amazoncorretto:24.0.1-alpine3.21

# Create a directory inside the container
WORKDIR /app

# Copy the jar file
COPY target/countryDB-0.0.1-SNAPSHOT.jar app.jar

# Run the jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
