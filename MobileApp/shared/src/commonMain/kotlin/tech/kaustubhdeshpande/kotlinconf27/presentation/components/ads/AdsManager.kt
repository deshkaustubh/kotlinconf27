package tech.kaustubhdeshpande.kotlinconf27.presentation.components.ads

interface AdsManager {
    fun initialize()
    val interstitialAdLoader: FullScreenAdLoader
    val rewardedAdLoader: FullScreenAdLoader
}
