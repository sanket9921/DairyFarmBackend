FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/your-app-name.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
