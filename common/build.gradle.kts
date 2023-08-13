import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("dev.architectury.loom")
    id("architectury-plugin")
    kotlin("jvm") version "1.8.0"
}

tasks.test {
    useJUnitPlatform()
    exclude("**/*IntegrationTest")
}

architectury {
    common("forge", "fabric")
}

loom {
    silentMojangMappingsLicense()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    minecraft("com.mojang:minecraft:${property("minecraft_version")}")
    // The following line declares the mojmap mappings, you may use other mappings as well
    mappings("net.fabricmc:yarn:1.19.2+build.4:v2")
    // We depend on fabric loader here to use the fabric @Environment annotations and get the mixin dependencies
    // Do NOT use other classes from fabric loader
    modImplementation("net.fabricmc:fabric-loader:${property("fabric_loader_version")}")
    implementation("net.kyori:adventure-text-minimessage:${property("minimessage_version")}")
    implementation("net.kyori:adventure-text-serializer-gson:${property("minimessage_version")}")
    implementation("net.kyori:adventure-text-serializer-legacy:${property("minimessage_version")}")
    // Remove the next line if you don't want to depend on the API
    modApi("dev.architectury:architectury:${property("architectury_version")}") { isTransitive = false }
    modImplementation("com.cobblemon:mod:1.3.1+1.19.2-SNAPSHOT") { isTransitive = false }
    implementation(kotlin("stdlib-jdk8"))

    implementation("mysql:mysql-connector-java:8.0.33")
}

repositories {
    mavenCentral()
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}