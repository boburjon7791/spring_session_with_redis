FROM openjdk:17
EXPOSE 8080
ADD build/libs/spring_session.jar spring_session.jar
ENTRYPOINT ["java","-jar","/spring_session.jar"]