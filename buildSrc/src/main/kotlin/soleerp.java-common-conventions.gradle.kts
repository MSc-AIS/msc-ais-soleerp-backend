plugins {
    java
    `maven-publish`
}

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
}

dependencies {
    // Apache commons io
    implementation("org.apache.commons:commons-lang3:3.11")
    // guava
    implementation("com.google.guava:guava:29.0-jre")
    // Use JUnit Jupiter API for testing.
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.2")
    // Use JUnit Jupiter Engine for testing.
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.test {
    useJUnitPlatform()
}
