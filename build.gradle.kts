import java.nio.charset.StandardCharsets

plugins {
    java
    `maven-publish`
}

repositories {
    maven {
        url = uri("https://papermc.io/repo/repository/maven-public/")
    }

    maven {
        url = uri("https://nexus.telesphoreo.me/repository/plex/")
    }

    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")
    compileOnly("io.papermc.paper:paper-api:1.18.2-R0.1-SNAPSHOT")
    compileOnly("dev.plex:server:1.1-SNAPSHOT")
    compileOnly("dev.plex:api:1.1-SNAPSHOT")
}

group = "dev.plex"
version = "1.0"
description = "ExampleModule"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}

tasks.getByName<Jar>("jar") {
    archiveBaseName.set("Plex-ExampleModule")
    archiveVersion.set("")
}

tasks {
    javadoc {
        options.memberLevel = JavadocMemberLevel.PRIVATE
    }
    compileJava {
        options.encoding = StandardCharsets.UTF_8.name()
    }
}