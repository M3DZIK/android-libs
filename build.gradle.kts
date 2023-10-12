// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    // trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false

    alias(libs.plugins.nexus.publish)
}

allprojects {
    // fix task (current target is 1.8) and 'kspDebugKotlin' task (current target is 17) jvm target compatibility should be set to the same Java version.
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}

nexusPublishing {
    this.repositories {
        sonatype {
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))

            username = providers.gradleProperty("ossrhUsername").get()
            password = providers.gradleProperty("ossrhPassword").get()

            group = "dev.medzik.android"
        }
    }
}
