plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.kmmcartdemoapp.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.example.kmmcartdemoapp.android"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("androidx.compose.ui:ui:1.2.1")
    implementation("androidx.compose.ui:ui-tooling:1.2.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.2.1")
    implementation("androidx.compose.foundation:foundation:1.2.1")
    implementation("androidx.compose.material:material:1.2.1")
    implementation("androidx.activity:activity-compose:1.5.1")

    // Compose dependencies
    implementation("com.github.skydoves:landscapist-glide:2.0.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
    implementation("androidx.navigation:navigation-compose:2.6.0-alpha01")
    implementation("com.google.accompanist:accompanist-flowlayout:0.17.0")
    implementation("androidx.compose.runtime:runtime-livedata:1.2.1")

    //Glide
    implementation ("com.github.bumptech.glide:glide:4.11.0")
    kapt("com.github.bumptech.glide:compiler:4.11.0")

    // Koin
    implementation("io.insert-koin:koin-android:3.2.1")

    //TODO: temo zbog DI
    //Ktor-Client
    implementation("io.ktor:ktor-client-core:1.6.3")
    implementation("io.ktor:ktor-client-android:1.6.3")
    implementation("io.ktor:ktor-client-serialization:1.6.3")

    //Json seri
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")

    //SQL Delight
    implementation("com.squareup.sqldelight:runtime:1.5.2")
    implementation("com.squareup.sqldelight:coroutines-extensions:1.5.3")
    implementation("com.squareup.sqldelight:android-driver:1.5.2")
}