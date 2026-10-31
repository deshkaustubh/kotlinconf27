package tech.kaustubhdeshpande.kotlinconf27.subscription.api

interface SubscriptionProviderFactory {
    companion object

    fun createProvider(): SubscriptionProvider

    fun createProviderUi(): SubscriptionProviderUi
}
