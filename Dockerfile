FROM openjdk:8-jdk-alpine

WORKDIR /usr/app

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} bin/match-player.jar

CMD ["java","-jar", "./bin/match-player.jar"]
