plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = Config.COMPILE_SDK

    defaultConfig {
        minSdk = Config.MIN_SDK_VERSION
        targetSdk = Config.TARGET_SDK
    }
}

dependencies {
    /** Modules **/
    implementation(project(Modules.MODULE_DOMAIN))

    /** Kotlin **/
    implementation(Kotlin.CORE)
    implementation(Kotlin.STDLIB)


    /**GoogleMap**/
    implementation(GoogleMaps.googleMap)

    /**Room**/
    kapt(Room.COMPILER)
    implementation(Room.RUN_TIME)
    implementation(Room.KTX)

    /** Retrofit **/
    implementation(Retrofit2.RETROFIT)
    implementation(Retrofit2.CONVERTER_JSON)
    implementation(Retrofit2.COROUTINES_ADAPTER)
    implementation(Retrofit2.LOGGING_INTERCEPTOR)

    /** Tests **/
    testImplementation(Tests.JUNIT)
    androidTestImplementation(Tests.TEST_EXT_JUNIT)
    androidTestImplementation(Tests.ESPRESSO)
}