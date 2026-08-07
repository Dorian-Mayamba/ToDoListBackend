FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy and explicitly rename the jar to a static name
COPY build/libs/*.jar /app/app.jar
COPY build/resources/main/application.yaml /app/config.yaml

EXPOSE 8080

# Run using the explicit, static jar name
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.location=file:/app/config.yaml"]