#FROM openjdk:8-jdk-alpine
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

FROM openjdk:8-jdk-alpine

COPY target/productservice-0.0.1-SNAPSHOT.jar .
CMD /usr/bin/java -Xmx400m -Xms400m -jar productservice-0.0.1-SNAPSHOT.jar
EXPOSE 8080
