package tech.kaustubhdeshpande.kotlinconf27.subscription.revenuecat

import tech.kaustubhdeshpande.kotlinconf27.subscription.api.SubscriptionProvider
import tech.kaustubhdeshpande.kotlinconf27.subscription.api.SubscriptionProviderFactory
import tech.kaustubhdeshpande.kotlinconf27.subscription.api.SubscriptionProviderUi

internal actual val subscriptionProviderFactory: SubscriptionProviderFactory
    get() =
        object : SubscriptionProviderFactory {
            override fun createProvider(): SubscriptionProvider = RevenueCatSubscriptionProvider()

            override fun createProviderUi(): SubscriptionProviderUi = RevenueCatSubscriptionProviderUi()
        }
