plugins {
    id("org.springframework.boot") version "3.0.6"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("java")
}

group = "it.unibz.digidojo"
version = "1.0"

repositories {
    mavenCentral()
    maven(url = "https://repo.spring.io/snapshot")
    maven(url = "https://repo.spring.io/milestone")
    maven(url = "https://repo.spring.io/release")
}

dependencies {
    implementation("org.jetbrains:annotations:23.0.0")
    implementation("org.json:json:20210307")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.0.6")
    testImplementation("junit:junit:4.13.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
    implementation("org.springframework:spring-beans:6.0.5")
    implementation("org.springframework:spring-context:6.0.5")
    implementation("org.springframework.boot:spring-boot-starter-web:3.0.6")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.0.6")
    implementation("javax.persistence:javax.persistence-api:2.2")
    implementation("org.postgresql:postgresql:42.6.0")
    implementation("org.apache.kafka:kafka-streams")
    implementation("org.springframework.kafka:spring-kafka")
    compileOnly("org.projectlombok:lombok")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("org.postgresql:postgresql")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.kafka:spring-kafka-test")
    compileOnly(project(":DigiDojoSharedModel"))
}

extra {
    val springCloudVersion = "2022.0.2"
    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}")
        }
    }
}

tasks.test {
    useJUnitPlatform()
    include("it/unibz/digidojo/activityplannerservice/domain/CalendarEventRepositoryTest.class")
}