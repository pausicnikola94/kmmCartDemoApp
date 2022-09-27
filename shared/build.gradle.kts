plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    kotlin("plugin.serialization")
    id("com.squareup.sqldelight")
}

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }



    sourceSets {
        val commonMain by getting {
            dependencies {
                // Coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

                // Koin
                implementation("io.insert-koin:koin-android:3.2.1")

                //Ktor-Client
                implementation("io.ktor:ktor-client-core:1.6.3")
                implementation("io.ktor:ktor-client-android:1.6.3")
                implementation("io.ktor:ktor-client-serialization:1.6.3")

                //Json seri
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")

                //SQL Delight
                implementation("com.squareup.sqldelight:runtime:1.5.2")
                implementation("com.squareup.sqldelight:coroutines-extensions:1.5.3")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                //SQL Delight
                implementation("com.squareup.sqldelight:android-driver:1.5.2")
            }
        }
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.example.kmmcartdemoapp"
    compileSdk = 32
    defaultConfig {
        minSdk = 26
        targetSdk = 32
    }
}

sqldelight {
    database("KMMCartDemoAppDatabase") {
        packageName = "com.example.kmmcartdemoapp"
    }
}

