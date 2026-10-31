package tech.kaustubhdeshpande.kotlinconf27.presentation.screens.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import tech.kaustubhdeshpande.kotlinconf27.data.repository.SubscriptionRepository
import tech.kaustubhdeshpande.kotlinconf27.data.repository.UserRepository
import tech.kaustubhdeshpande.kotlinconf27.designsystem.components.SettingsItemUiState
import tech.kaustubhdeshpande.kotlinconf27.designsystem.generated.resources.UiRes
import tech.kaustubhdeshpande.kotlinconf27.designsystem.generated.resources.ic_settings_item_logout
import tech.kaustubhdeshpande.kotlinconf27.designsystem.generated.resources.ic_settings_item_subscriptions
import tech.kaustubhdeshpande.kotlinconf27.designsystem.generated.resources.ic_settings_item_support_legal
import tech.kaustubhdeshpande.kotlinconf27.domain.model.isFree
import tech.kaustubhdeshpande.kotlinconf27.generated.resources.Res
import tech.kaustubhdeshpande.kotlinconf27.generated.resources.help_and_support
import tech.kaustubhdeshpande.kotlinconf27.generated.resources.logout
import tech.kaustubhdeshpande.kotlinconf27.generated.resources.subscriptions
import tech.kaustubhdeshpande.kotlinconf27.root.AppConfiguration
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AccountViewModel(
    private val userRepository: UserRepository,
    subscriptionRepository: SubscriptionRepository,
) : ViewModel() {

    private val subscriptionsItem = SettingsItemUiState(
        startIcon = UiRes.drawable.ic_settings_item_subscriptions,
        textRes = Res.string.subscriptions,
    )
    private val supportItem = SettingsItemUiState(
        startIcon = UiRes.drawable.ic_settings_item_support_legal,
        textRes = Res.string.help_and_support,
    )
    private val logoutItem = SettingsItemUiState(
        startIcon = UiRes.drawable.ic_settings_item_logout,
        textRes = Res.string.logout,
        showEndIcon = false,
    )

    private fun settingsItemsFor(isSignedIn: Boolean): List<SettingsItemUiState> = buildList {
        // Subscriptions row only when premium features are enabled.
        if (AppConfiguration.PREMIUM_FEATURES_ENABLED) add(subscriptionsItem)
        add(supportItem)
        if (AppConfiguration.AUTH_SOCIAL_LOGIN_ENABLED && isSignedIn) add(logoutItem)
    }

    private val _uiState = MutableStateFlow(AccountUiState())
    val uiState: StateFlow<AccountUiState> =
        combine(
            userRepository.currentUser,
            subscriptionRepository.currentSubscriptionFlow,
            _uiState,
        ) { currentUser, currentSubscription, uiState ->
            val user = currentUser.getOrNull()
            uiState.copy(
                user = if (user?.isAnonymous == true && AppConfiguration.AUTH_SOCIAL_LOGIN_ENABLED) null else user,
                settingsItemList = settingsItemsFor(isSignedIn = user != null),
                showUpgradePremiumBanner = AppConfiguration.PREMIUM_FEATURES_ENABLED && currentSubscription.isFree,
            )
        }.stateIn(viewModelScope, WhileSubscribed(5000), _uiState.value)

    fun onUiEvent(event: AccountUiEvent) = viewModelScope.launch {
        when (event) {
            AccountUiEvent.OnLogoutConfirmClick -> {
                userRepository.logOut()
                _uiState.update { it.copy(isLogoutDialogVisible = false) }
            }

            AccountUiEvent.OnLogoutDialogDismiss -> {
                _uiState.update { it.copy(isLogoutDialogVisible = false) }
            }

            is AccountUiEvent.OnSettingsItemClick -> {
                when (event.item.textRes) {
                    Res.string.logout -> {
                        _uiState.update { it.copy(isLogoutDialogVisible = true) }
                    }

                    else -> {}
                }
            }

            AccountUiEvent.OnClickUpgradePremium -> Unit

            AccountUiEvent.OnClickProfile -> Unit

            AccountUiEvent.OnClickSignIn -> Unit
        }
    }
}
