FROM openjdk:21
COPY resoursec src/main/resources
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar" , "/app.jar"]