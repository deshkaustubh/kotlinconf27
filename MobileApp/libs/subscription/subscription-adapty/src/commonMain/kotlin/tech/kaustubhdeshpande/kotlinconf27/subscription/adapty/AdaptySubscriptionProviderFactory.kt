package tech.kaustubhdeshpande.kotlinconf27.subscription.adapty

import tech.kaustubhdeshpande.kotlinconf27.subscription.api.SubscriptionProviderFactory

val SubscriptionProviderFactory.Companion.Adapty: SubscriptionProviderFactory
    get() = subscriptionProviderFactory

internal expect val subscriptionProviderFactory: SubscriptionProviderFactory
