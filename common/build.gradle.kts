plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    `maven-publish`
}

dependencies {
    compileOnly(kotlin("stdlib"))

    compileOnly("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    compileOnly("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0")
}

kotlin {
    jvmToolchain(21)
}

tasks {
    jar {
        archiveBaseName.set("${rootProject.name}-common")
        archiveVersion.set(project.version.toString())
        archiveClassifier.set("") 
    }

    val sourcesJar by registering(Jar::class) {
        archiveBaseName.set("${rootProject.name}-common")
        archiveClassifier.set("sources")
        from(sourceSets.main.get().allSource)
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            groupId = project.group.toString()
            artifactId = "${rootProject.name}-common"
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
