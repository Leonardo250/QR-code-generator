FROM mave:4.0.0-eclipse-temurin-21 AS build
WORKDIR /APP
COPY pom.xml .
COPY src ./src
RUN mvn clean -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /APP
COPY --from=build /app/target/*.jar app.jar

ARG AWS_ACCESS_KEY_ID
ARG AWS_SECRET_ACESS_KEY

ENV AWS_REGION=us-east-1
ENV AWS_S3_BUCKET=qrcode-storage

ENTRYPOINT["java","-jar","app.jar"]
