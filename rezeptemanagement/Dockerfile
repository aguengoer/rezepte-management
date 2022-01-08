#Build maven
FROM maven:3.6.1-jdk-11 AS MAVEN_BUILD
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn package -DskipTests


FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=/build/target/*.jar

WORKDIR /opt/app
COPY --from=MAVEN_BUILD ${JAR_FILE} app.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]
