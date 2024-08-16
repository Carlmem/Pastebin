FROM gradle:8.1.0-jdk17 AS build
MAINTAINER OLEKSANDR <Carlmem> RADIONOV
COPY --chown=gradle:gradle src /home/gradle/project
WORKDIR /home/gradle/project
RUN gradle clean build -x test
FROM openjdk:17

RUN mkdir /app
COPY --from=build /home/gradle/project/profile-service/build/libs/*.jar /app/application.jar
CMD ["java", "-jar","/app/application.jar"]