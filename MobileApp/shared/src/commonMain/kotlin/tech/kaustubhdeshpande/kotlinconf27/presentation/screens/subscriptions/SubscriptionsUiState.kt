package tech.kaustubhdeshpande.kotlinconf27.presentation.screens.subscriptions

import tech.kaustubhdeshpande.kotlinconf27.domain.model.Subscription

data class SubscriptionsUiState(
    val isLoading: Boolean = false,
    val showUpgradePremiumBanner: Boolean = true,
    val currentPlan: Subscription? = null,
    val isMock: Boolean = false,
)

sealed interface SubscriptionsUiEvent {
    data object OnCancelMockSubscription : SubscriptionsUiEvent
}
