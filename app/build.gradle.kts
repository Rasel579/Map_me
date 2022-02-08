plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    compileSdk = Config.COMPILE_SDK

    defaultConfig {
        applicationId = Config.APPLICATION_ID
        minSdk = Config.MIN_SDK_VERSION
        targetSdk = Config.TARGET_SDK
        versionCode = Config.VERSION_CODE
        versionName = Config.VERSION_NAME
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = Config.java_version
        targetCompatibility = Config.java_version
    }
    kotlinOptions {
        jvmTarget = Config.JVM_TARGET
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    /** Modules **/
    implementation(project(Modules.MODULE_DATA))
    implementation(project(Modules.MODULE_DOMAIN))
    implementation(project(Modules.MODULE_UI))
    /** Koin **/
    implementation(Koin.CORE)
    implementation(Koin.ANDROID)
    implementation(Koin.ANDROID_COMPAT)

    /**ViewBinding Delegate**/
    implementation(ViewBindingDelegate.DELEGATE)

    /**Google Map**/
    implementation(GoogleMaps.googleMap)


    implementation(Kotlin.CORE)
    implementation(Design.APPCOMPAT)
    implementation(Design.MATERIAL)
    implementation(Design.CONSTRAINT_LAYOUT)
    implementation(Design.NAVIGATION_FRAGMENT)
    implementation(Design.NAVIGATION_UI_KTX)
    testImplementation(Tests.JUNIT)
    androidTestImplementation(Tests.TEST_EXT_JUNIT)
    androidTestImplementation(Tests.ESPRESSO)
}
secrets {
    defaultPropertiesFileName = "local.properties.defaults"
}
