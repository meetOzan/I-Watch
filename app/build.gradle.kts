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

        testInstrumentationRunner = libs.versions.testInstrumentationRunner.get()
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "API_KEY", "\"3852bf2a5fec7d1433a266636bcb0302\"")
        buildConfigField("String", "MOVIE_BASE_URL", "\"https://api.themoviedb.org/3/\"")
        buildConfigField("String", "POSTER_BASE_PATH", "\"https://image.tmdb.org/t/p/original\"")

    }

    android {
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

    implementation(libs.androidx.core.ktx) /*1.9.0*/
    implementation(libs.androidx.lifecycle.runtime.ktx) /*2.6.1*/
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.activity.activity.compose)
    implementation(composeBom)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.util)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext) /* 1.1.5 */
    androidTestImplementation(libs.androidx.test.espresso.core) /* 3.5.1 */
    androidTestImplementation(composeBom)
    androidTestImplementation(libs.junit)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    implementation(libs.android.compose.ui)
    implementation(libs.android.compose.material)

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

    // Chucker
    implementation(libs.com.github.chuckerteam.chucker)

    // Lottie
    implementation(libs.com.airbnb.android.lottie)

    // Firebase - Auth
    implementation(libs.com.firebase.auth.ktx)

    // Firebase - Firestore
    implementation(libs.firebase.firestore.ktx)

}