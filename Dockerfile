FROM alpine:latest
LABEL authors="ridwan"
RUN apk add gcompat
COPY ./target/testquarkus-1.0-SNAPSHOT-runner /tmp
WORKDIR /tmp
CMD ["./testquarkus-1.0-SNAPSHOT-runner"]


#FROM busybox:1.37.0-glibc
#LABEL authors="ridwan"
#COPY ./target/native-image-demo /tmp
#WORKDIR /tmp
#CMD ["./native-image-demo"]

#FROM bellsoft/liberica-runtime-container:jre-slim
#LABEL authors="ridwan"
#FROM bellsoft/liberica-openjdk-alpine
#COPY ./target/demo-0.0.1-SNAPSHOT.jar /tmp
#WORKDIR /tmp
#CMD ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]

