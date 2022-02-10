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

    /** Kotlin **/
    implementation(Kotlin.STDLIB)

    /** Retrofit **/
    implementation(Retrofit2.RETROFIT)
    implementation(Retrofit2.CONVERTER_JSON)
    implementation(Retrofit2.COROUTINES_ADAPTER)
    implementation(Retrofit2.LOGGING_INTERCEPTOR)

    /** GoogleMap **/
    implementation(GoogleMaps.googleMap)

    /** Gson **/
    implementation(Retrofit2.CONVERTER_JSON)

    /**Room**/
    kapt(Room.COMPILER)
    implementation(Room.RUN_TIME)
    implementation(Room.KTX)


    /** Coroutines **/
    implementation(Coroutines.ANDROID)
    implementation(Coroutines.CORE)

    /** Tests **/
    testImplementation(Tests.JUNIT)
}