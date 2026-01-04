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
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
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