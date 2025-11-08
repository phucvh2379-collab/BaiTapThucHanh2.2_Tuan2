plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    // Thêm plugin Google Services cho Firebase
    id("com.google.gms.google-services") version "4.4.2" apply false
}

android {
    namespace = "com.example.uthsmarttasks"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.uthsmarttasks"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // Compose Icons (Giữ nguyên)
    implementation("br.com.devsrsouza.compose.icons:font-awesome:1.1.0")

    // Navigation (Giữ nguyên)
    implementation("androidx.navigation:navigation-compose:2.8.0")
    implementation("androidx.compose.foundation:foundation:1.6.0")

    // Core (Giữ nguyên)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Compose BOM & UI (Giữ nguyên)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    // ==========================================================
    // CÁC THƯ VIỆN THÊM VÀO CHO FIREBASE VÀ GOOGLE SIGN-IN
    // ==========================================================

    // Firebase BOM (Quản lý phiên bản)
    implementation(platform("com.google.firebase:firebase-bom:33.1.0"))

    // Firebase Authentication
    implementation("com.google.firebase:firebase-auth-ktx")

    // Google Sign-In (Bắt buộc cho đăng nhập bằng Google)
    implementation("com.google.android.gms:play-services-auth:21.2.0")

    // Coil (Để tải ảnh đại diện từ Google URL trong ProfileScreen)
    implementation("io.coil-kt:coil-compose:2.6.0")


    // ==========================================================
    // TESTING (Giữ nguyên)
    // ==========================================================
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

}
