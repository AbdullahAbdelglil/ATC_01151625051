FROM maven:3.8.4-openjdk-17 AS build

WORKDIR /app

COPY pom.xml .
RUN mnv dependency:go-offline

COPY src ./src
RUN .\mvnw clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/FullStackEventBookingSystem-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/target/FullStackEventBookingSystem-0.0.1-SNAPSHOT.jar"]