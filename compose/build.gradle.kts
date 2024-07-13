plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "dev.medzik.android.compose"
    compileSdk = libs.versions.android.sdk.compile.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.sdk.min.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    compileOnly(libs.compose.material.icons)
    compileOnly(libs.compose.material3)
    compileOnly(libs.compose.navigation)

    implementation(libs.medzik.common.kotlin)

    debugImplementation(libs.compose.material.icons)
    debugImplementation(libs.compose.material3)
    debugImplementation(libs.compose.navigation)

    androidTestImplementation(libs.test.junit)
    androidTestImplementation(libs.test.espresso.core)
    androidTestImplementation(libs.compose.ui.test.junit4)

    testImplementation(libs.test.junit)
    testImplementation(libs.compose.runtime)

    debugImplementation(libs.compose.ui.test.manifest)

    // for preview support
    debugImplementation(libs.compose.ui.tooling)
    implementation(libs.compose.ui.tooling.preview)
}

afterEvaluate {
    publishConfig {
        artifactId = "compose"

        pom {
            name.set("Jetpack Compose Utils")
            description.set("Jetpack Compose Utilities library")
        }
    }
}
