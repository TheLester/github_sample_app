object Versions {
    // Build Config
    const val minSDK = 17
    const val compileSDK = 28
    const val targetSDK = 28

    // App version
    const val appVersionCode = 1
    const val appVersionName = "1.0.0"

    // Plugins
    const val androidGradlePlugin = "3.2.0-rc02"

    // Kotlin
    const val kotlin = "1.2.61"

    // Architecture components
    const val arch = "2.0.0-rc01"

    // Support
    const val support = "1.0.0-rc01"
    const val constraintLayout = "1.1.0"

    const val retrofit = "2.4.0"
    const val okHttp = "3.4.0"
    const val gson = "2.8.5"
    const val dagger2 = "2.16"
    const val picasso = "2.71828"
    const val rxJava2 = "2.2.1"
    const val rxAndroid = "2.1.0"

    // Tools
    const val stetho = "1.5.0"

    // Parceler
    const val parceler = "1.1.11"

    // Testing
    const val junit = "4.12"

    const val test = "1.1.0-alpha1"
    const val espresso = "3.1.0-alpha1"
    const val mockWebServer = "1.0.2"

}

object Deps {
    // Plugins
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"

    // Kotlin
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"

    // Architecture components
    const val lifecycle = "android.arch.lifecycle:extensions:${Versions.arch}"
    const val paging = "androidx.paging:paging-runtime:${Versions.arch}"
    const val pagingRxJava2 = "androidx.paging:paging-rxjava2:${Versions.arch}"

    // Support Library
    const val appCompat = "androidx.appcompat:appcompat:${Versions.support}"
    const val supportAnnotations = "androidx.annotation:annotation:${Versions.support}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val material = "com.google.android.material:material:${Versions.support}"

    // Network
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val rxJava2Converter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"

    // JSON parsing
    const val gson = "com.google.code.gson:gson:${Versions.gson}"

    // RX
    const val rxJava2 = "io.reactivex.rxjava2:rxjava:${Versions.rxJava2}"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"

    // DI
    const val dagger2 = "com.google.dagger:dagger:${Versions.dagger2}"
    const val dagger2Compiler = "com.google.dagger:dagger-compiler:${Versions.dagger2}"
    const val dagger2Android = "com.google.dagger:dagger-android:${Versions.dagger2}"
    const val dagger2AndroidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger2}"
    const val dagger2AndroidProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger2}"

    // Image Loading
    const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"

    // Parceler
    const val parceler = "org.parceler:parceler-api:${Versions.parceler}"
    const val parcelerProcessor = "org.parceler:parceler:${Versions.parceler}"

    // Tools
    const val stetho = "com.facebook.stetho:stetho-okhttp3:${Versions.stetho}"

    // Testing
    const val junit = "junit:junit:${Versions.junit}"

    const val rules = "androidx.test:rules:${Versions.test}"
    const val testRunner = "androidx.test:runner:${Versions.test}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val espressoContrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso}"
    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.okHttp}"

}