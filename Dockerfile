FROM amazoncorretto:11.0.13
COPY target/gcpstorage-0.0.1-SNAPSHOT.jar /demo.jar
WORKDIR /

ENTRYPOINT ["java", "-jar", "demo.jar"]
