plugins {
    kotlin("jvm") version "1.5.31"
}

group = "ralcoll"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val junitVersion = "5.8.1"
val hamkrestVersion = "1.8.0.1"
val result4kVersion = "1.11.2.1"

dependencies {
    implementation("com.natpryce:hamkrest:$hamkrestVersion")
    implementation("dev.forkhandles:result4k:$result4kVersion")
    implementation(platform("dev.forkhandles:forkhandles-bom:$result4kVersion"))

    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

tasks.test {
    useJUnitPlatform()
}