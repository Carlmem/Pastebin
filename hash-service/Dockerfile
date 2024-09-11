FROM gradle:8.1.0-jdk17 AS build
MAINTAINER OLEKSANDR <Carlmem> RADIONOV
COPY --chown=gradle:gradle . /home/gradle/Pastebin
WORKDIR /home/gradle/Pastebin
RUN gradle clean hash-service:build

FROM openjdk:17
RUN mkdir /app
COPY --from=build /home/gradle/Pastebin/hash-service/build/libs/*.jar /app/application.jar
CMD ["java", "-jar", "/app/application.jar"]