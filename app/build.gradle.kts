plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.oghenekume.reimaginedtelegram"
    compileSdk = 37

    defaultConfig {
        applicationId = "com.oghenekume.reimaginedtelegram"
        minSdk = 26
        targetSdk = 37
        versionCode = 1
        versionName = "0.1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        buildConfig = true
    }

    lint {
        abortOnError = true
    }
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    testImplementation(libs.junit)
}
