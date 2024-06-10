plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.navigation.safeargs)
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
}

android {
    namespace = "com.legalist.quranrhbr"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.legalist.quranrhbr"
        minSdk = 27
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":data"))
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.dynamicFeatures.fragment)
    implementation(libs.fragment.ktx)
    implementation(libs.gson)
    implementation(libs.swiperefreshlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(project(":domain"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    

    // viewpager and dotsIndicator

    implementation("androidx.viewpager2:viewpager2:1.0.0")
    implementation("com.tbuonomo:dotsindicator:4.3")

    // splash screen
    implementation("androidx.core:core-splashscreen:1.0.1")

    // pin view OTP
    implementation ("io.github.chaosleung:pinview:1.4.4")



    var hilt_version = "2.44"
    var room_version = "2.5.0"
    var retrofit_version= "2.9.0"
     val rxJavaVersion = "2.1.1"  // 2.6.6 not working



    // Google Material Components, UI bileşenlerini kullanmak için
    implementation ("com.google.android.material:material:1.9.0")


    // AndroidX Lifecycle, LiveData bileşeni, verileri gözlemlemek için
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")

    // AndroidX Lifecycle, ViewModel bileşeni, UI ile veri yönetimi için
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")

    // AndroidX Room, veritabanı erişim kütüphanesi (runtime), SQLite ile çalışmak için
    implementation ("androidx.room:room-runtime:$room_version")

    // AndroidX Room, veritabanı erişim kütüphanesi (compiler), SQLite için derleme zamanı desteği
    kapt ("androidx.room:room-compiler:$room_version")

    // AndroidX Room, Kotlin genişletmeleri, Room ile daha kolay çalışmak için
    implementation ("androidx.room:room-ktx:$room_version")

    // Retrofit, RESTful web servislerine erişim için HTTP client
    implementation ("com.squareup.retrofit2:retrofit:$retrofit_version")

    // Retrofit, JSON verileri dönüştürmek için Gson kullanımı
    implementation ("com.squareup.retrofit2:converter-gson:$retrofit_version")

    implementation ("com.squareup.retrofit2:adapter-rxjava2:2.9.0")



    implementation ("com.google.protobuf:protobuf-javalite:3.18.0")




    // Dagger Hilt, bağımlılık enjeksiyonu kütüphanesi (runtime), Hilt kullanarak bağımlılık yönetimi için
    implementation ("com.google.dagger:hilt-android:$hilt_version")

    // Dagger Hilt, bağımlılık enjeksiyonu kütüphanesi (compiler), Hilt için derleme zamanı desteği
    kapt ("com.google.dagger:hilt-android-compiler:$hilt_version")

    implementation ("androidx.appcompat:appcompat:1.3.0")
    implementation ("com.squareup.retrofit2:adapter-rxjava2:$retrofit_version")

    implementation ("io.reactivex.rxjava2:rxjava:$rxJavaVersion")
    implementation ("io.reactivex.rxjava2:rxandroid:$rxJavaVersion")

    implementation ("androidx.datastore:datastore-preferences:1.0.0-alpha01")

    // Lifecycle component & Kotlin coroutines components
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.2.0")
    api ( "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9")
    api ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")

    implementation ("com.jakewharton.timber:timber:4.7.1")
    implementation ("com.otaliastudios:zoomlayout:1.9.0")



}
