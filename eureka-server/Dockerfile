FROM maven:3.8.7-eclipse-temurin-19
LABEL authors="Екатерина"
COPY target/eureka-server-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8761
ENTRYPOINT ["java", "-jar", "app.jar"]