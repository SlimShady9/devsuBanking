# --- STAGE 1: Build the application ---
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /home/app

COPY . .
ARG DOMAIN_NAME=customer
RUN mvn clean package -pl ${DOMAIN_NAME}/${DOMAIN_NAME}-infrastructure -am -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
ARG DOMAIN_NAME=customer
COPY --from=build /home/app/${DOMAIN_NAME}/${DOMAIN_NAME}-infrastructure/target/*.jar app.jar
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]