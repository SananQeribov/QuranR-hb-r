plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
}

android {
    namespace = "com.legalist.mylibrary"
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
    implementation(project(":domain"))
//    implementation(project(":domain"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)




    var hilt_version = "2.44"
    var room_version = "2.5.0"
    var retrofit_version= "2.9.0"




    // Google Material Components, UI bileşenlerini kullanmak için
    implementation ("com.google.android.material:material:1.9.0")

    // AndroidX Lifecycle, LiveData bileşeni, verileri gözlemlemek için
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")

    // AndroidX Lifecycle, ViewModel bileşeni, UI ile veri yönetimi için
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")

    // AndroidX Room, veritabanı erişim kütüphanesi (runtime), SQLite ile çalışmak için
    implementation ("androidx.room:room-runtime:$room_version")

    kapt ("androidx.room:room-compiler:$room_version")

    // AndroidX Room, Kotlin genişletmeleri, Room ile daha kolay çalışmak için
    implementation ("androidx.room:room-ktx:$room_version")

    // Retrofit, RESTful web servislerine erişim için HTTP client
    implementation ("com.squareup.retrofit2:retrofit:$retrofit_version")

    // Retrofit, JSON verileri dönüştürmek için Gson kullanımı
    implementation ("com.squareup.retrofit2:converter-gson:$retrofit_version")

    // Dagger Hilt, bağımlılık enjeksiyonu kütüphanesi (runtime), Hilt kullanarak bağımlılık yönetimi için
    implementation ("com.google.dagger:hilt-android:$hilt_version")

    // Dagger Hilt, bağımlılık enjeksiyonu kütüphanesi (compiler), Hilt için derleme zamanı desteği
    kapt ("com.google.dagger:hilt-android-compiler:$hilt_version")
}