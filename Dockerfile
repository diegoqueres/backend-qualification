FROM openjdk:11
EXPOSE 8085
ADD target/backend-qualification.jar backend-qualification.jar
ENTRYPOINT ["java","-jar","backend-qualification.jar"]