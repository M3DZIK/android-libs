// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    // trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.serialization) apply false

    alias(libs.plugins.nexus.publish)
}

nexusPublishing {
    this.repositories {
        sonatype {
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))

            username.set(providers.gradleProperty("ossrhUsername").orNull)
            password.set(providers.gradleProperty("ossrhPassword").orNull)

            group = "dev.medzik.android"
        }
    }
}
