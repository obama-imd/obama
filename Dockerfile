FROM openjdk:17-jdk-alpine
ENV JWT_SECRET=test
ENV SENHA_APP_EMAIL='test'
ENV SPRING_PROFILE=local
COPY build/libs/*.jar /app.jar
EXPOSE 8081
ENTRYPOINT ["java","-Dspring.profiles.active=$SPRING_PROFILE","-jar", "/app.jar"]
