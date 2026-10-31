package tech.kaustubhdeshpande.kotlinconf27.util

import tech.kaustubhdeshpande.kotlinconf27.data.source.featureflag.FeatureFlagManager
import tech.kaustubhdeshpande.kotlinconf27.presentation.components.ads.AdsManager
import tech.kaustubhdeshpande.kotlinconf27.presentation.components.ads.IosAdsDisplayer
import tech.kaustubhdeshpande.kotlinconf27.util.analytics.Analytics

/**
This factory is used to help to use swift libraries in KMP. Actual implementations are provided in swift.
 */
interface SwiftLibDependencyFactory {
    fun provideFeatureFlagManagerImpl(): FeatureFlagManager
    fun provideFirebaseAnalyticsImpl(): Analytics
    fun provideAdsManagerImpl(): AdsManager
    fun provideIosAdsDisplayer(): IosAdsDisplayer
}
