package tech.kaustubhdeshpande.kotlinconf27.presentation.screens.account

import tech.kaustubhdeshpande.kotlinconf27.designsystem.components.SettingsItemUiState
import tech.kaustubhdeshpande.kotlinconf27.designsystem.generated.resources.UiRes
import tech.kaustubhdeshpande.kotlinconf27.designsystem.generated.resources.ic_settings_item_logout
import tech.kaustubhdeshpande.kotlinconf27.designsystem.generated.resources.ic_settings_item_subscriptions
import tech.kaustubhdeshpande.kotlinconf27.designsystem.generated.resources.ic_settings_item_support_legal
import tech.kaustubhdeshpande.kotlinconf27.domain.model.User
import tech.kaustubhdeshpande.kotlinconf27.generated.resources.Res
import tech.kaustubhdeshpande.kotlinconf27.generated.resources.help_and_support
import tech.kaustubhdeshpande.kotlinconf27.generated.resources.logout
import tech.kaustubhdeshpande.kotlinconf27.generated.resources.subscriptions

data class AccountUiState(
    val settingsItemList: List<SettingsItemUiState> = listOf(
        SettingsItemUiState(
            startIcon = UiRes.drawable.ic_settings_item_subscriptions,
            textRes = Res.string.subscriptions,
        ),

        SettingsItemUiState(
            startIcon = UiRes.drawable.ic_settings_item_support_legal,
            textRes = Res.string.help_and_support,
        ),

        SettingsItemUiState(
            startIcon = UiRes.drawable.ic_settings_item_logout,
            textRes = Res.string.logout,
            showEndIcon = false,
        ),
    ),
    val user: User? = null,
    val isLogoutDialogVisible: Boolean = false,
    val showUpgradePremiumBanner: Boolean = false,
)

sealed interface AccountUiEvent {
    data class OnSettingsItemClick(val item: SettingsItemUiState) : AccountUiEvent
    data object OnLogoutConfirmClick : AccountUiEvent
    data object OnLogoutDialogDismiss : AccountUiEvent
    data object OnClickUpgradePremium : AccountUiEvent
    data object OnClickSignIn : AccountUiEvent
    data object OnClickProfile : AccountUiEvent
}
