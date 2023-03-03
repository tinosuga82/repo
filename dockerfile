# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="tino1997@hotmail.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 5000 available to the world outside this container
EXPOSE 5000

# The application's jar file
ARG JAR_FILE=target/backendDevtest-*.jar

# Add the application's jar to the container
ADD ${JAR_FILE} backendDevtest-*.jar

ENV TZ=Europe/Madrid
#RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
RUN apk add tzdata
RUN cd /usr/share/zoneinfo
RUN cp /usr/share/zoneinfo/$TZ /etc/localtime


# Run the jar file 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/backendDevtest-*.jar"]