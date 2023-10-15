import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.4"
    id("io.spring.dependency-management") version "1.1.3"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
    kotlin("plugin.jpa") version "1.8.22"
}

group = "com.kotlinspring"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.0.4")

    implementation("org.springframework.boot:spring-boot-starter-validation:3.0.4")

    implementation("org.springframework.boot:spring-boot-starter-web:3.1.0")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.2")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    //logging
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")

//    runtimeOnly("com.h2database:h2:2.1.214")
    runtimeOnly("org.postgresql:postgresql")

    testImplementation("org.springframework.boot:spring-boot-starter-test:3.1.0")
    testImplementation("org.springframework.boot:spring-boot-starter-webflux:3.0.4")
    testImplementation("io.mockk:mockk:1.13.4")
    testImplementation("com.ninja-squad:springmockk:4.0.2")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

sourceSets {
    test {
        java {
            setSrcDirs(listOf("src/test/intg", "src/test/unit"))
        }
    }
}