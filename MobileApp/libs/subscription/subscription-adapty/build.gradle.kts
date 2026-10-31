plugins {
    id("configure-kmp-library-module")
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose)
}

kotlin {
    android {
        namespace = "tech.kaustubhdeshpande.kotlinconf27.subscription.adapty"
    }

    sourceSets {
        commonMain.dependencies {
            api(projects.libs.subscription.subscriptionApi)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
        }
        mobileMain.dependencies {
            api(libs.adapty)
            api(libs.adapty.ui)
        }
    }
}
