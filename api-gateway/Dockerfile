FROM maven:3.8.7-eclipse-temurin-19
LABEL authors="Екатерина"
COPY target/api-gateway-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]