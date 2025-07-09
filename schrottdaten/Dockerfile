ARG BUILD_HOME=/build

FROM gradle:jdk21 AS build
ARG BUILD_HOME
COPY --chown=gradle:gradle gradlew build.gradle.kts settings.gradle.kts $BUILD_HOME/
COPY --chown=gradle:gradle gradle $BUILD_HOME/gradle
COPY --chown=gradle:gradle src $BUILD_HOME/src
WORKDIR $BUILD_HOME
RUN ./gradlew build --no-daemon

FROM eclipse-temurin:21-jdk-alpine
ARG BUILD_HOME
COPY --from=build $BUILD_HOME/build/libs/schrottdaten-0.0.1.jar schrottdaten.jar
ENTRYPOINT ["java", "-jar", "schrottdaten.jar"]
