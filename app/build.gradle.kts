@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.androidx.navigation.safe.args)
    id("com.google.gms.google-services")
}

android {
    namespace = libs.versions.namespace.get()
    compileSdk = (libs.versions.android.compile.sdk.get()).toInt()

    defaultConfig {
        applicationId = libs.versions.applicationId.get()
        minSdk = (libs.versions.android.min.sdk.get()).toInt()
        targetSdk = (libs.versions.android.target.sdk.get()).toInt()
        versionCode = (libs.versions.versionCode.get()).toInt()

        versionName = libs.versions.version.name.get()
        resourceConfigurations.plus(listOf("en","tr"))

        testInstrumentationRunner = libs.versions.testInstrumentationRunner.get()
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "API_KEY", "\"3852bf2a5fec7d1433a266636bcb0302\"")
        buildConfigField("String", "MOVIE_BASE_URL", "\"https://api.themoviedb.org/3/\"")
        buildConfigField("String", "POSTER_BASE_PATH", "\"https://image.tmdb.org/t/p/original\"")

        androidResources {
            generateLocaleConfig = true
        }

        buildFeatures {
            buildConfig = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = libs.versions.jvmTarget.get()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    val composeBom = platform(libs.androidx.compose.bom)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.activity.activity.compose)
    implementation(composeBom)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.util)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.android.compose.ui)
    implementation(libs.android.compose.material)
    implementation(libs.firebase.crashlytics.buildtools)

    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(composeBom)
    androidTestImplementation(libs.junit)

    // Debug
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // AppCompat
    implementation(libs.androidx.appcompat)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    // Retrofit
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp.logging)

    // Room
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)
    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)
    implementation(libs.room.stdlib)

    // Coil
    implementation(libs.coil.kt.compose)

    // Navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.navigation.compose.viewmodel)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.play.services)

    // Chucker
    implementation(libs.com.github.chuckerteam.chucker)

    // Lottie
    implementation(libs.com.airbnb.android.lottie)

    // Firebase - Auth
    implementation(libs.com.firebase.auth.ktx)

    // Firebase - Firestore
    implementation(libs.firebase.firestore.ktx)

    // DataStore
    implementation(libs.androidx.datastore.preferences)

    // System UI Controller
    implementation(libs.google.accompanist.systemuicontroller)

    // Snacky - 3rd party lib.
    implementation(libs.com.github.snacky)

    // Snackie - 3rd party lib.
    implementation(libs.com.github.snackie)

    // Swipe - 3rd party lib.
    implementation(libs.me.saket.swipe)

    // Process-Phoenix - 3rd party lib.
    implementation(libs.github.process.phoneix)

}