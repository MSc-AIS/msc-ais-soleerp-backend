plugins {
    id("msc.ais.soleerp.java-library-conventions")
}

dependencies {
    implementation(project(":model"))
    implementation(project(":db"))
    // model to freemarker (html) libraries
    implementation("org.freemarker:freemarker:2.3.30")
    implementation("org.jsoup:jsoup:1.13.1")
    // html to pdf library
    implementation("com.openhtmltopdf:openhtmltopdf-pdfbox:1.0.4")
}
