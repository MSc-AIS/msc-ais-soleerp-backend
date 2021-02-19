plugins {
    id("msc.ais.soleerp.java-library-conventions")
}

dependencies {
    implementation(project(":model"))
    implementation("org.postgresql:postgresql:42.2.18")
    implementation("org.apache.commons:commons-dbcp2:2.8.0")
}
