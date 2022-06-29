FROM openjdk:11

COPY build/libs/obama-0.0.1-SNAPSHOT.jar obama.jar

ENTRYPOINT ["java", "-jar", "/pflege-accounting-service.jar"]
