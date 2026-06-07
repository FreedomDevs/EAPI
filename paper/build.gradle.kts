plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("com.github.johnrengelman.shadow")
    `maven-publish`
}

repositories {
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    implementation(project(":common"))

    compileOnly("io.papermc.paper:paper-api:1.21.1-R0.1-SNAPSHOT")

    compileOnly("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    compileOnly("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0")
}

kotlin {
    jvmToolchain(21)
}

tasks {
    shadowJar {
        archiveBaseName.set("${rootProject.name}-paper")
        archiveVersion.set(project.version.toString())
        archiveClassifier.set("")
        mergeServiceFiles()

        dependencies {
            exclude(dependency("org.jetbrains.kotlin:kotlin-stdlib"))
            exclude(dependency("org.jetbrains:annotations"))
        }
    }

    jar {
        enabled = false
    }

    build {
        dependsOn(shadowJar)
    }

    processResources {
        filesMatching("plugin.yml") {
            expand(mapOf("version" to project.version))
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            groupId = project.group.toString()
            artifactId = "${rootProject.name}-paper"
            version = project.version.toString()
        }
        create<MavenPublication>("mavenJavaLatest") {
            from(components["java"])

            groupId = project.group.toString()
            artifactId = "${rootProject.name}-paper"
            version = "latest"
        }
    }
    repositories {
        maven {
            url = rootProject.layout.buildDirectory.dir("repo").get().asFile.toURI()
        }
    }
}
