plugins {
    id("msc.ais.soleerp.java-library-conventions")
}

dependencies {
    implementation(project(":model"))
    implementation(project(":db"))
}
