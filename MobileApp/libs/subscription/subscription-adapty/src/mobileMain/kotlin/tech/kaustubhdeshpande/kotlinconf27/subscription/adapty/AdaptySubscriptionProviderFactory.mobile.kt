package tech.kaustubhdeshpande.kotlinconf27.subscription.adapty

import tech.kaustubhdeshpande.kotlinconf27.subscription.api.SubscriptionProvider
import tech.kaustubhdeshpande.kotlinconf27.subscription.api.SubscriptionProviderFactory
import tech.kaustubhdeshpande.kotlinconf27.subscription.api.SubscriptionProviderUi

internal actual val subscriptionProviderFactory: SubscriptionProviderFactory
    get() =
        object : SubscriptionProviderFactory {
            override fun createProvider(): SubscriptionProvider = AdaptySubscriptionProvider()

            override fun createProviderUi(): SubscriptionProviderUi = AdaptySubscriptionProviderUi()
        }
