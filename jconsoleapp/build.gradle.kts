plugins {
    kotlin("jvm")

    // To run this as a standalone app in Terminal with command: ./gradlew run
    application
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

// Entry point for running the app
application {
    mainClass.set("com.example.jconsoleapp.MainJava")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "1.8"  // Make Kotlin target JVM 1.8 too
    }
}