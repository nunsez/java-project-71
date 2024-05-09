plugins {
    id("java")
    id("application")
    id("checkstyle")
}

group = "hexlet.code"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    // https://picocli.info
    implementation("info.picocli:picocli:4.7.6")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

application {
    mainClass = "hexlet.code.App"
}

tasks.test {
    useJUnitPlatform()
}
