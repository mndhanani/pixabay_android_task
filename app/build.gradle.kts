import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.kapt)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.kotlin.parcelize)

    // Gradle plugin for passing arguments using Navigation component
    alias(libs.plugins.navigation.safeargs)
}

// Read the secret.properties file
val secretPropertiesFile = rootProject.file("secret.properties")
val secretProperties = Properties()

if (secretPropertiesFile.exists()) {
    secretProperties.load(secretPropertiesFile.inputStream())
}

val apiKey: String = secretProperties["API_KEY"] as String? ?: ""

android {
    namespace = "com.task.pixabay"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.task.pixabay"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        // Inject API Key into BuildConfig
        buildConfigField("String", "API_KEY", "\"$apiKey\"")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    // Enable data binding
    dataBinding {
        enable = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.material)

    // Hilt - Dependency Injection
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // Coroutines for async tasks
    implementation(libs.coroutines.android)

    // Lifecycle components (LiveData, ViewModel)
    implementation(libs.lifecycle.livedata)
    implementation(libs.lifecycle.viewmodel)

    // Data Binding for XML and UI binding
    implementation(libs.databinding.runtime)

    // Navigation Component
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    // Glide for image loading and caching
    implementation(libs.glide)

    // Retrofit for network calls
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    // Paging 3
    implementation(libs.paging.runtime)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}