plugins {
    kotlin("jvm") version "1.9.22"
    application
}

group = "com.proyecto.backend"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Servidor Web REST (Javalin) + JSON + Logs
    implementation("io.javalin:javalin:6.1.3")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")
    implementation("org.slf4j:slf4j-simple:2.0.12")

    // Base de Datos (SQLite + Exposed ORM)
    implementation("org.xerial:sqlite-jdbc:3.45.1.0")
    implementation("org.jetbrains.exposed:exposed-core:0.48.0")
    implementation("org.jetbrains.exposed:exposed-dao:0.48.0")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.48.0")
}

application {
    mainClass.set("com.proyecto.backend.MainKt")
}