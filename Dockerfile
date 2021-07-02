#
# Build stage
#
#FROM maven:3.6.0-jdk-11-slim AS build
#COPY src /home/app/src
#COPY pom.xml /home/app
#RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM alpine:3.14
#COPY --from=build /home/app/target/webservices-0.0.1-SNAPSHOT.jar /usr/local/lib/ws.jar
COPY /target/webservices-0.0.1-SNAPSHOT.jar /usr/local/lib/ws.jar
EXPOSE 8080
RUN apk add openjdk11-jre
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /usr/local/lib/ws.jar
