@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.androidx.navigation.safe.args)
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

        buildConfigField ("String", "API_KEY", "\"Your API Key\"")
        buildConfigField("String","MOVIE_BASE_URL", "\"https://api.themoviedb.org/\"")
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

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // Retrofit
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp.logging)

    // Room
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)
    implementation(libs.room.runtime)

    // Coil
    implementation(libs.coil.kt.compose)

    // Navigation
    implementation(libs.androidx.navigation.compose)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)

}