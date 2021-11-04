FROM amazoncorretto:11.0.13
COPY target/gcpstorage-0.0.1-SNAPSHOT.jar /demo.jar
ADD credentials.json /credentials.json
WORKDIR /
#ENV GOOGLE_APPLICATION_CREDENTIALS=/credentials.json
ENV GOOGLE_APPLICATION_CREDENTIALS=sm://gcstest
ENTRYPOINT ["java", "-jar", "demo.jar"]
