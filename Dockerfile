FROM gradle:jdk17-amazoncorretto AS gradle
WORKDIR /app
COPY . .
RUN gradle bootJar

FROM openjdk:17 AS runtime
WORKDIR /app
ENV PORT 8080
ENV SPRING_PROFILE production
ARG JAR_FILE=/app/build/libs/*.jar

RUN chown -R 1000:1000 /app
USER 1000:1000

ENTRYPOINT ["java", "-jar", "-Dserver.port=${PORT}", "-Dspring.profiles.active=${SPRING_PROFILE}", "app.jar"]