FROM openjdk:8-jdk-alpine
COPY build/libs/ms_ibm-0.0.1-SNAPSHOT.jar ms_ibm.jar
ENTRYPOINT ["java","-jar","/ms_ibm.jar"]