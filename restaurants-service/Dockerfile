FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR /app

RUN apt-get update && apt-get install -y wget unzip && \
    wget https://services.gradle.org/distributions/gradle-8.10.2-bin.zip && \
    unzip gradle-8.10.2-bin.zip -d /opt/gradle && \
    ln -s /opt/gradle/gradle-8.10.2/bin/gradle /usr/bin/gradle

COPY . .
RUN gradle clean build -x test --no-daemon

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]