FROM tomcat:10.1.13-jdk17-temurin-jammy

ARG WAR_FILE
ARG CONTEXT

COPY ${WAR_FILE} /usr/local/tomcat/webapps/${CONTEXT}.war