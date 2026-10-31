package tech.kaustubhdeshpande.kotlinconf27.subscription.revenuecat

import tech.kaustubhdeshpande.kotlinconf27.subscription.api.SubscriptionProviderFactory

val SubscriptionProviderFactory.Companion.RevenueCat: SubscriptionProviderFactory
    get() = subscriptionProviderFactory

internal expect val subscriptionProviderFactory: SubscriptionProviderFactory
