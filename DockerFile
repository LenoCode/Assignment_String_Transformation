FROM gradle:7.6-jdk17-alpine@sha256:6c3a45d54ef5b988654154bff18a93889a11b654c6e045dc7734f9806393c49d as BUILD

WORKDIR /app

COPY . .

RUN gradle build

RUN gradle customFatJar


#----------------------------
FROM  eclipse-temurin:17-alpine@sha256:1ecf4fe36cb342580299254487b5d899a19c5238bbac06070ebc107f513eb473 as SERVE

WORKDIR /app

COPY --from=BUILD /app/build/libs/Assignment-1.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]