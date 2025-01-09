
plugins {
    kotlin("jvm")
}

group = "com.merelythis"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.larksuite.oapi:oapi-sdk:2.4.4")
    implementation("org.slf4j:slf4j-reload4j:2.0.16")
    implementation(project(":base"))
}

kotlin {
    jvmToolchain(17)
}