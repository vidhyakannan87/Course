FROM maven:3.5.4-jdk-8-alpine

WORKDIR /Course
COPY . .


# Build the app
RUN mvn package -DskipTests=true -Dmaven.test.skip=true


EXPOSE 8080

ENTRYPOINT ["bash", "-cl"]
CMD ["java -jar target/Course-0.0.1-SNAPSHOT.jar"]



