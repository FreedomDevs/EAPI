plugins {
    kotlin("jvm") version "2.1.20" apply false
    kotlin("plugin.serialization") version "2.1.20" apply false
    id("com.github.johnrengelman.shadow") version "8.1.1" apply false
}

group = "dev.elysium.eapi"
version = "2.0"

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    extensions.configure<org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension> {
        jvmToolchain(21)
    }
}
