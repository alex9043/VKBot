FROM openjdk:23-jdk-slim

WORKDIR /app

COPY app/VKBot-0.0.1-SNAPSHOT.jar /app/app.jar

CMD ["java", "-jar", "app.jar"]