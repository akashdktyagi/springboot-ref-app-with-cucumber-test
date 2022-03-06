FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 9096
ENTRYPOINT ["java","-jar","/app.jar"]