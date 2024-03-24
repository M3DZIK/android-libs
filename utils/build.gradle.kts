plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "dev.medzik.android.utils"
    compileSdk = libs.versions.android.sdk.compile.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.sdk.min.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        buildConfig = false
    }
}

dependencies {
    compileOnly(libs.androidx.annotation)
    compileOnly(libs.kotlinx.coroutines)

    debugImplementation(libs.androidx.annotation)
    debugImplementation(libs.kotlinx.coroutines)
}

afterEvaluate {
    publishConfig {
        artifactId = "utils"

        pom {
            name.set("Android Common Utilities")
            description.set("Android Utilities for Application Development - Common")
        }
    }
}
