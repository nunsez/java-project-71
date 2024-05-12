plugins {
    java
    application
    checkstyle
    jacoco
}

group = "hexlet.code"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/org.jetbrains/annotations
//    implementation("org.jetbrains:annotations:24.1.0")

    // https://picocli.info
    implementation("info.picocli:picocli:4.7.6")
    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.1")
    // https://mvnrepository.com/artifact/com.fasterxml.jackson.dataformat/jackson-dataformat-yaml
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.17.1")


//    // https://mvnrepository.com/artifact/org.jacoco/org.jacoco.core
//    implementation("org.jacoco:org.jacoco.core:0.8.12")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // https://mvnrepository.com/artifact/org.assertj/assertj-core
    testImplementation("org.assertj:assertj-core:3.25.3")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

application {
    mainClass = "hexlet.code.App"
}

tasks.test {
    useJUnitPlatform()
//    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report

    reports {
        xml.required = true
//        xml.outputLocation = layout.buildDirectory.file("jacoco/xml/jacocoTestReport.xml")
        html.required = true
//        html.outputLocation = layout.buildDirectory.dir("jacoco/html")
        csv.required = false
    }
}
