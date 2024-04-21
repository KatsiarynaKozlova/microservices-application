FROM maven:3.8.7-eclipse-temurin-19
LABEL authors="Екатерина"
COPY target/BookService-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]