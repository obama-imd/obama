FROM openjdk:17-alpine

COPY build/libs/obama-0.0.1-SNAPSHOT.jar obama.jar

ENTRYPOINT ["java", "-jar", "/obama.jar"]
