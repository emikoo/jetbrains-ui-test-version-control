plugins {
    kotlin("jvm") version "2.1.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

    maven { url = uri("https://www.jetbrains.com/intellij-repository/releases") }
    maven { url = uri("https://www.jetbrains.com/intellij-repository/snapshots") }
    maven { url = uri("https://packages.jetbrains.team/maven/p/ij/intellij-dependencies")}
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.1.10")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")

    testImplementation("com.jetbrains.intellij.tools:ide-starter-squashed:LATEST-EAP-SNAPSHOT")
    testImplementation("com.jetbrains.intellij.tools:ide-starter-junit5:LATEST-EAP-SNAPSHOT")
    testImplementation("com.jetbrains.intellij.tools:ide-starter-driver:LATEST-EAP-SNAPSHOT")
    testImplementation("com.jetbrains.intellij.driver:driver-sdk:LATEST-EAP-SNAPSHOT")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}