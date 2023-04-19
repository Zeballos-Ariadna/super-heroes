## BUILD ##
FROM maven:3.8.5-openjdk-17-slim AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY . .
RUN mvn clean package -Dmaven.test.skip
## RUN ##
FROM openjdk:17-alpine
COPY --from=builder /app/target/super-heroes-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD ["java", "-jar", "/super-heroes-0.0.1-SNAPSHOT.jar", "-Xms512M","-Xmx1024M"]