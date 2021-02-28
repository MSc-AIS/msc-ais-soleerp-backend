plugins {
    id("msc.ais.soleerp.java-application-conventions")
}

dependencies {
    implementation(project(":model"))
    implementation(project(":service"))
    implementation(project(":db"))
    implementation("io.javalin:javalin:3.12.0")
}

application {
    mainClass.set("msc.ais.soleerp.api.Application")
}

tasks.register<Jar>("uberJar") {
    archiveClassifier.set("msc-ais-soleerp-1.0.0")
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    from(sourceSets.main.get().output)

    manifest {
        attributes(mapOf("Main-Class" to "msc.ais.soleerp.api.Application"))
    }

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}
