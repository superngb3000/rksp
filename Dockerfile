FROM openjdk:17-slim AS builder

WORKDIR /home/

ARG JAR_FILE=./fileStorage-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

FROM builder
ENTRYPOINT ["java","-jar","app.jar"]

EXPOSE 8080