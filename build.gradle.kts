plugins {
    id "architectury-plugin" version "3.4-SNAPSHOT"
    id "dev.architectury.loom" version "1.1-SNAPSHOT" apply false
}

architectury {
    minecraft = rootProject.minecraft_version
}

subprojects {
    apply plugin: "dev.architectury.loom"

    loom {
        silentMojangMappingsLicense()
    }

    dependencies {
        minecraft "com.mojang:minecraft:${rootProject.minecraft_version}"
        // The following line declares the mojmap mappings, you may use other mappings as well
        mappings loom.officialMojangMappings()
        // The following line declares the yarn mappings you may select this one as well.
        // mappings "net.fabricmc:yarn:@YARN_MAPPINGS@:v2"
    }
}

allprojects {
    apply plugin: "java"
    apply plugin: "architectury-plugin"
    apply plugin: "maven-publish"

    archivesBaseName = rootProject.archives_base_name
    version = rootProject.mod_version
    group = rootProject.maven_group

    repositories {
        // Add repositories to retrieve artifacts from in here.
        // You should only use this when depending on other mods because
        // Loom adds the essential maven repositories to download Minecraft and libraries from automatically.
        // See https://docs.gradle.org/current/userguide/declaring_repositories.html
        // for more information about repositories.
    }

    tasks.withType(JavaCompile) {
        options.encoding = "UTF-8"
        options.release = 17
    }

    java {
        withSourcesJar()
    }
}
