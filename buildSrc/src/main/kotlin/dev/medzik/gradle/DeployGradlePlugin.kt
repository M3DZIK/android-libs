
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.TaskProvider
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.registering
import org.gradle.plugins.signing.SigningExtension

private fun Project.publishing(): PublishingExtension {
    return extensions.getByType(PublishingExtension::class.java)
}

private fun Project.signing(): SigningExtension {
    return extensions.getByType(SigningExtension::class.java)
}

fun Project.publishConfig(configuration: MavenPublication.() -> Unit) {
    project.apply {
        plugin("maven-publish")
        plugin("signing")
        plugin("org.jetbrains.dokka")
    }

    project.publishing().apply {
        publications {
            create<MavenPublication>("mavenJava") {
                groupId = "dev.medzik.android"
                version = "1.8.0"

                configuration()

                val javadocJar: TaskProvider<Jar> by project.tasks.registering(Jar::class) {
                    dependsOn("dokkaHtml")
                    archiveClassifier.set("javadoc")
                    from("${project.projectDir}/build/dokka/html")
                }
                artifact(javadocJar)

                pom {
                    url = "https://github.com/M3DZIK/android-libs"

                    licenses {
                        license {
                            name.set("MIT")
                            url.set("https://github.com/M3DZIK/android-libs/blob/main/LICENSE")
                        }
                    }

                    developers {
                        developer {
                            name.set("Oskar Karpiński")
                            email.set("me@medzik.dev")
                        }
                    }

                    scm {
                        connection.set("scm:git@github.com:M3DZIK/android-libs.git")
                        developerConnection.set("scm:git@github.com:M3DZIK/android-libs.git")
                        url.set("scm:git@github.com:M3DZIK/android-libs.git")
                    }
                }

                project.afterEvaluate {
                    from(components["release"])
                }
            }
        }
    }

    project.signing().apply {
        sign(project.publishing().publications["mavenJava"])
        useGpgCmd()
    }
}
