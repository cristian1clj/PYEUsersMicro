FROM openjdk:21
COPY target/PYE-API-0.0.1-SNAPSHOT.jar PYEUsersMicro.jar
ENTRYPOINT ["java", "-jar", "PYEUsersMicro.jar"]