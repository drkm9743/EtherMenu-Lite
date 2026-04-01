import org.gradle.jvm.tasks.Jar
import java.util.Properties

plugins {
    java
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.isFork = true
    options.forkOptions.javaHome = file(project.findProperty("jdk25Home")?.toString()
        ?: System.getenv("JDK25_HOME")
        ?: "")
}

fun loadProperties(): Properties {
    return Properties().apply {
        project.file("src/main/resources/EtherMenu/EtherMenu.properties").inputStream().use {
            load(it)
        }
    }
}

group = "EtherMenu"
version = loadProperties().getProperty("version").replace("'", "")

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.projectlombok:lombok:1.18.42")
    annotationProcessor("org.projectlombok:lombok:1.18.42")
    implementation("org.ow2.asm:asm:9.9.1")
    implementation("org.ow2.asm:asm-tree:9.9.1")
    implementation(files("lib/fmod.jar"))
    implementation(files("lib/zombie.jar"))
    implementation(files("lib/Kahlua.jar"))
    implementation(files("lib/org.jar"))
}

// ASM-only configuration (no game JARs — they're read from projectzomboid.jar at runtime)
val asmOnly: Configuration by configurations.creating {
    isCanBeResolved = true
}
dependencies {
    asmOnly("org.ow2.asm:asm:9.9.1")
    asmOnly("org.ow2.asm:asm-tree:9.9.1")
}

tasks.named<Jar>("jar") {
    destinationDirectory.set(project.file("build"))
    archiveFileName.set("EtherMenu-${version}-lite.jar")

    manifest {
        attributes["Main-Class"] = "EtherMenu.Main"
    }

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    // Only bundle ASM — game libraries are on the classpath at runtime
    from(asmOnly.map { file ->
        if (file.isDirectory) file else zipTree(file)
    })
}
