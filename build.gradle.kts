plugins {
    id("java")
}

group = "tech.jomarm"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    compileOnly(files("libs/Kindlet-1.3.jar"))
}

tasks.withType<JavaCompile>() {
    options.compilerArgs.addAll(listOf("-source", "1.4", "-target", "1.4"))
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "tech.jomarm.helloworldkindlet.Main"
        attributes["Implementation-Title"] = "Hello World Kindlet"
        attributes["Implementation-Version"] = "1.0.0"
        attributes["Implementation-Vendor"] = "Jomar Milan"
        attributes["Extension-List"] = "SDK"
        attributes["SDK-Extension-Name"] = "com.amazon.kindle.kindlet"
        attributes["SDK-Extension-Version"] = "1.3"
        attributes["Toolbar-Mode"] = "persistent"
        attributes["Font-Size-Mode"] = "point"
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}

tasks.test {
    useJUnitPlatform()
}

// Create an empty gradle task
tasks.register("signJar") {
    dependsOn(tasks.jar)

    doLast {
        val signdir = File("$buildDir/sign")
        signdir.mkdirs()
        val jar = tasks.jar.get().archiveFile.get().asFile
        val keystore = File("$rootDir/developer.keystore")
        val storepass = "password"
        val aliases = listOf("dktest", "ditest", "dntest")
        val sigalg = "SHA1withDSA"
        val digestalg = "SHA1"

        for (alias in aliases) {
            exec {
                commandLine("jarsigner", "-keystore", keystore, "-storepass", storepass, "-sigalg", sigalg, "-digestalg", digestalg, jar, alias)
            }
        }
    }
}