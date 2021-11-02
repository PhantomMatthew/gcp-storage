FROM amazoncorretto:11.0.13
COPY target/gcpstorage-0.0.1-SNAPSHOT.jar /demo.jar
ADD credentials.json /credentials.json
WORKDIR /
ENV GOOGLE_APPLICATION_CREDENTIALS=/credentials.json
ENTRYPOINT ["java", "-jar", "demo.jar"]
