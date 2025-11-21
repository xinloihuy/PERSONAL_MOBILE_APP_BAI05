plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.baitap05"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.baitap05"
        minSdk = 23
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
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    // --- Bắt đầu thêm Dependencies cho API/Retrofit2 ---
    // 1. Retrofit Core
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    // 2. Gson Converter (Để xử lý JSON)
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    // 3. OkHttp và Logging Interceptor (Để xem request/response khi debug)
    implementation("com.squareup.okhttp3:okhttp:4.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")
    //load ảnh với Glide
    implementation("com.github.bumptech.glide:glide:4.14.2")
    annotationProcessor("com.github.bumptech.glide:compiler:4.14.2")
}