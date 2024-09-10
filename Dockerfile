FROM gradle:8.8-jdk17-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:17-jre-slim

EXPOSE 8808

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/*.jar /app/spring-data-redis-cache.jar

ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/spring-data-redis-cache.jar"]