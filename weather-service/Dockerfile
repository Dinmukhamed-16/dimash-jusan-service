FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/weather-service.jar weather-service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "weather-service.jar"]
