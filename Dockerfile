FROM gradle:8.1.0-jdk17 AS build
ARG SUBPROJECT_NAME
MAINTAINER OLEKSANDR <Carlmem> RADIONOV

WORKDIR /home/gradle/Pastebin
COPY --chown=gradle:gradle . /home/gradle/Pastebin

WORKDIR /home/gradle/Pastebin/$SUBPROJECT_NAME
RUN gradle clean ${SUBPROJECT_NAME}:build

FROM openjdk:17
RUN mkdir /app

COPY --from=build /home/gradle/Pastebin/$SUBPROJECT_NAME/build/libs/*.jar /app/application.jar
CMD ["java", "-jar", "/app/application.jar"]