FROM maven:3.8.6-amazoncorretto-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM amazoncorretto:17
COPY --from=build /target/quizapp-0.0.1-SNAPSHOT.war quizapp.war
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "quizapp.war"]