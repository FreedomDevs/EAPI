plugins {
    kotlin("jvm") version "2.1.20" apply false
    kotlin("kapt") version "2.1.20" apply false
    kotlin("plugin.serialization") version "2.1.20" apply false
    id("com.gradleup.shadow") version "9.4.2" apply false
}

allprojects {
    group = "dev.elysium.eapi"
    repositories {
        mavenCentral()
    }
}

subprojects {
    version = "2.5"

    apply(plugin = "org.jetbrains.kotlin.jvm")

    extensions.configure<org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension> {
        jvmToolchain(21)
    }
}
