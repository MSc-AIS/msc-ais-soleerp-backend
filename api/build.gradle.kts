plugins {
    id("sole.erp.backend.java-application-conventions")
}

dependencies {
    implementation("org.apache.commons:commons-text")
}

application {
    mainClass.set("sole.erp.backend.api.Application")
}
