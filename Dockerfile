FROM openjdk:17
EXPOSE 8080
ADD build/libs/spring_session-0.0.1-SNAPSHOT.jar.jar spring_session-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/spring_session-0.0.1-SNAPSHOT.jar"]
