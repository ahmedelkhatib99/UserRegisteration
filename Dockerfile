# define base docker image
FROM maven:3.8.3-openjdk-17
LABEL maintainer="java.net"
ADD target/task-0.0.1-SNAPSHOT.jar task.jar
ENTRYPOINT ["java", "-jar", "task.jar"]