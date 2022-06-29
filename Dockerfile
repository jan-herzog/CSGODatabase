FROM openjdk:17-alpine
COPY ./CSGODatabase-0.0.1-SNAPSHOT.jar /app/app.jar
WORKDIR /root/
ENTRYPOINT ["java","-jar","/app/app.jar"]