plugins {
    kotlin("jvm") version "2.3.0"
}

group = "ly.algobase"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:6.0.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
    testImplementation ("io.kotest:kotest-assertions-core:6.0.7")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

tasks.register<JavaExec>("run") {
    mainClass = "ly.algobase.MainKt"
    classpath = sourceSets["main"].runtimeClasspath
}