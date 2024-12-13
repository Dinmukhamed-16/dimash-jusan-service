#Интеграционный сервис с погодой (тестовое задание)
Проект выполнен в качестве тестового задания, реализован микросервис для интеграции с сервисом weatherapi.com
для получения:
- Прогноз погоды на данный момент;
- Прогноз погоды на несколько дней;

#Сервисы
- City Service - сервис, предназначенный для управления данными о городах;
- Weather Service - сервис, который непосредственно работает со сторонним сервисом weatherapi;

# Технологии

- Spring Boot — основной фреймворк для создания микросервисов.
- Java 17 — используемая версия языка программирования.
- H2 Database — база данных для хранения данных о городах.
- Docker — контейнеризация для упрощения развертывания.
- Prometheus — мониторинг микросервисов.


#Инструкция
- git clone https://github.com/Dinmukhamed-16/dimash-jusan-service.git
- mvn clean install

#Сервис будет доступен по адресу:

- City Service: http://localhost:8081 (swagger: http://localhost:8081/swagger-ui/index.html)
- Weather Service: http://localhost:8082 (swagger: http://localhost:8082/swagger-ui/index.html)