FROM openjdk:11-jre-slim
COPY build/libs/fileservice-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java"]
CMD ["-jar","/app.jar"]
