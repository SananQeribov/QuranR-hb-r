plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id ("kotlin-kapt")

}

android {
    namespace = "com.legalist.domain"
    compileSdk = 34

    defaultConfig {
        minSdk = 27

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)

    implementation(libs.androidx.appcompat)
    implementation(libs.material)
//    implementation(project(":data"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    var room_version = "2.5.0"

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.retrofit2:adapter-rxjava2:2.9.0")

    // Lifecycle component & Kotlin coroutines components
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.2.0")
    api ( "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9")
    api ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    //rx java
    val rxJavaVersion = "2.1.1"
    implementation ("io.reactivex.rxjava2:rxjava:$rxJavaVersion")
    implementation ("io.reactivex.rxjava2:rxandroid:$rxJavaVersion")
    // AndroidX Lifecycle, ViewModel bileşeni, UI ile veri yönetimi için
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")

    // AndroidX Room, veritabanı erişim kütüphanesi (runtime), SQLite ile çalışmak için
    implementation ("androidx.room:room-runtime:$room_version")

    kapt ("androidx.room:room-compiler:$room_version")

    // AndroidX Room, Kotlin genişletmeleri, Room ile daha kolay çalışmak için
    implementation ("androidx.room:room-ktx:$room_version")




}