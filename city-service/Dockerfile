FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/city-service.jar city-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "city-service.jar"]
