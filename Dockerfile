FROM openjdk:8-jre-slim

RUN mkdir /app
COPY /target/*.jar /app/app.jar
COPY /src/main/resources/StoredCredential /app/StoredCredential

ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]
