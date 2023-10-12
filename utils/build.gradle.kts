plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "dev.medzik.android.utils"
    compileSdk = libs.versions.android.sdk.compile.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.sdk.min.get().toInt()

//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    compileOnly(libs.androidx.annotation)
    compileOnly(libs.kotlinx.coroutines.android)

    debugImplementation(libs.androidx.annotation)
    debugImplementation(libs.kotlinx.coroutines.android)

//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso.core)
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
