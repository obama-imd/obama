FROM openjdk:17-jdk-alpine

COPY build/libs/*.jar /app.jar

ENV JWT_SECRET=teste
ENV SENHA_APP_EMAIL=test
ENV DB_URL=teste
ENV DB_USERNAME=test
ENV DB_PASSWORD=test

EXPOSE 8081
ENTRYPOINT ["java","-Dspring.profiles.active=dev","-jar", "/app.jar"]
