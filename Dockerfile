FROM openjdk:8-jre-alpine
WORKDIR app
COPY target/goosegame-1.0-SNAPSHOT-jar-with-dependencies.jar app.jar
CMD ["java","-jar","app.jar"]