plugins {
    id("msc.ais.soleerp.java-application-conventions")
}

dependencies {
    implementation(project(":db"))
    implementation("io.javalin:javalin:3.12.0")
}

application {
    mainClass.set("msc.ais.soleerp.api.Application")
}
