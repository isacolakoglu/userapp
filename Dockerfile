FROM openjdk:22-jdk
WORKDIR /app
COPY target/userapp-0.0.1-SNAPSHOT.jar userapp.jar
ENTRYPOINT [ "java", "-jar", "userapp.jar" ]