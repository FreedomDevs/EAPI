plugins {
    kotlin("jvm")
    kotlin("kapt")
    kotlin("plugin.serialization")
    id("com.gradleup.shadow")

    `maven-publish`
    `java-library`
}

repositories {
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    api(project(":common"))

    compileOnly("com.velocitypowered:velocity-api:3.5.0-SNAPSHOT")
    compileOnly("me.lucko.configurate:configurate-toml:4.1")
    kapt("com.velocitypowered:velocity-api:3.5.0-SNAPSHOT")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0")
}

kotlin {
    jvmToolchain(21)
}

tasks {
    shadowJar {
        archiveBaseName.set("${rootProject.name}-velocity")
        archiveVersion.set(project.version.toString())
        archiveClassifier.set("shaded")
        mergeServiceFiles()

        minimize()

        relocate("kotlin", "dev.elysium.eapi.internal.kotlin")
        relocate("kotlinx", "dev.elysium.eapi.internal.kotlinx")
    }

    jar {
        enabled = true
        archiveBaseName.set("${rootProject.name}-velocity")
        archiveClassifier.set("")
    }

    build {
        dependsOn(shadowJar)
    }

    val sourcesJar by registering(Jar::class) {
        archiveBaseName.set("${rootProject.name}-velocity")
        archiveClassifier.set("sources")
        from(sourceSets.main.get().allSource)
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            groupId = project.group.toString()
            artifactId = "${rootProject.name}-velocity"
            version = project.version.toString()

            artifact(tasks.named("sourcesJar"))
        }
    }

    repositories {
        maven {
            url = rootProject.layout.buildDirectory.dir("repo").get().asFile.toURI()
        }
    }
}
