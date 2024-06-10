plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "dev.medzik.android.crypto"
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
        buildConfig = false
    }
}

dependencies {
    implementation(libs.androidx.datastore)
    implementation(libs.libcrypto)

    androidTestImplementation(libs.kotlinx.serialization.json)
    androidTestImplementation(libs.test.junit)
    androidTestImplementation(libs.test.espresso.core)
}

afterEvaluate {
    publishConfig {
        artifactId = "crypto"

        pom {
            name.set("Android Crypto Utilities")
            description.set("Android Utilities for Application Development - Crypto")
        }
    }
}
