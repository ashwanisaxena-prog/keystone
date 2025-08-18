FROM eclipse-temurin:17-jre
COPY target/keystone-0-jar-with-dependencies.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

