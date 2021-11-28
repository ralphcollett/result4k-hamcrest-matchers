plugins {
    kotlin("jvm") version "1.5.31"
    `maven-publish`
    signing
    id("net.researchgate.release") version "2.8.1"
}

group = "com.ralphcollett"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val junitVersion = "5.8.1"
val hamkrestVersion = "1.8.0.1"
val result4kVersion = "1.11.2.1"

dependencies {
    api("com.natpryce:hamkrest:$hamkrestVersion")
    api("dev.forkhandles:result4k:$result4kVersion")
    api(platform("dev.forkhandles:forkhandles-bom:$result4kVersion"))

    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

tasks.test {
    useJUnitPlatform()
}

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = "result4k-hamkrest-matchers"
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
            pom {
                name.set("result4k-hamkrest-matchers")
                description.set("Result4k Hamkrest matchers.")
                url.set("https://github.com/ralphcollett/result4k-hamkrest-matchers")
                developers {
                    developer {
                        id.set("ralph.collett")
                        name.set("Ralph Collett")
                        email.set("ralph.collett@gmail.com")
                    }
                }
                scm {
                    connection.set("git@github.com:ralphcollett/result4k-hamkrest-matchers.git")
                }
            }
        }

    }
    repositories {
        maven {
            val releasesRepoUrl = layout.buildDirectory.dir("repos/releases")
            val snapshotsRepoUrl = layout.buildDirectory.dir("repos/snapshots")
            url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)
        }
    }
}

signing {
    sign(publishing.publications["mavenJava"])
}