plugins {
    base

    // Publishing.
    id("io.github.gradle-nexus.publish-plugin") version "1.3.0" // Needs to be applied to the root project.
}

group = "io.github.typesafegithub"
version = "1.5.1-SNAPSHOT"

nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
        }
    }
    packageGroup.set("io.github.typesafegithub")
}

val setIsSnapshotFlagInGithubOutput by tasks.registering {
    doLast {
        val filePath = System.getenv("GITHUB_OUTPUT") ?: error("Expected GITHUB_OUTPUT variable to be set!")
        val isSnapshot = project.version.toString().endsWith("-SNAPSHOT")
        File(filePath).appendText("is-snapshot=$isSnapshot\n")
    }
}
