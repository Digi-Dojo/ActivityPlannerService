FROM gradle:7.4.2-jdk17 AS base

USER gradle

WORKDIR /usr/src/digidojo

COPY --chown=gradle:gradle ./DigiDojoSharedModel ./DigiDojoSharedModel

COPY --chown=gradle:gradle ./ActivityPlannerService ./ActivityPlannerService

FROM base AS builder

WORKDIR /usr/src/digidojo/ActivityPlannerService

RUN gradle clean bootJar

FROM eclipse-temurin:17-jdk AS runner

WORKDIR /digidojo

ENV PORT=8100

EXPOSE ${PORT}

COPY --from=builder /usr/src/digidojo/ActivityPlannerService/build/libs/*.jar ./service.jar

ENTRYPOINT ["java", "-jar", "/digidojo/service.jar"]