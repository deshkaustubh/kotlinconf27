package tech.kaustubhdeshpande.kotlinconf27.presentation.screens.subscriptions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import tech.kaustubhdeshpande.kotlinconf27.designsystem.components.LoadingProgress
import tech.kaustubhdeshpande.kotlinconf27.designsystem.components.LoadingProgressMode
import tech.kaustubhdeshpande.kotlinconf27.designsystem.components.ScreenWithToolbar
import tech.kaustubhdeshpande.kotlinconf27.designsystem.components.premium.CurrentSubscriptionPlanAndFeatures
import tech.kaustubhdeshpande.kotlinconf27.designsystem.components.premium.ManageSubscriptionText
import tech.kaustubhdeshpande.kotlinconf27.designsystem.components.premium.UpgradePremiumBanner
import tech.kaustubhdeshpande.kotlinconf27.designsystem.components.premium.UpgradePremiumBannerStyle
import tech.kaustubhdeshpande.kotlinconf27.designsystem.generated.resources.UiRes
import tech.kaustubhdeshpande.kotlinconf27.designsystem.generated.resources.ic_back
import tech.kaustubhdeshpande.kotlinconf27.designsystem.theme.AppTheme
import tech.kaustubhdeshpande.kotlinconf27.domain.model.Subscription
import tech.kaustubhdeshpande.kotlinconf27.generated.resources.Res
import tech.kaustubhdeshpande.kotlinconf27.generated.resources.paywall_unit_day
import tech.kaustubhdeshpande.kotlinconf27.generated.resources.paywall_unit_lifetime
import tech.kaustubhdeshpande.kotlinconf27.generated.resources.paywall_unit_month
import tech.kaustubhdeshpande.kotlinconf27.generated.resources.paywall_unit_month_count
import tech.kaustubhdeshpande.kotlinconf27.generated.resources.paywall_unit_week
import tech.kaustubhdeshpande.kotlinconf27.generated.resources.paywall_unit_year
import tech.kaustubhdeshpande.kotlinconf27.generated.resources.subscriptions
import tech.kaustubhdeshpande.kotlinconf27.generated.resources.subscriptions_plan_free
import tech.kaustubhdeshpande.kotlinconf27.presentation.components.premium.PremiumFeatureFactory
import tech.kaustubhdeshpande.kotlinconf27.util.Constants.subscriptionUrl
import tech.kaustubhdeshpande.kotlinconf27.util.extensions.asFormattedDate
import org.jetbrains.compose.resources.pluralStringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SubscriptionsScreen(
    modifier: Modifier = Modifier,
    viewModel: SubscriptionsViewModel,
    onNavigatePaywall: () -> Unit,
    onClickBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if (uiState.isLoading) {
        LoadingProgress(mode = LoadingProgressMode.FULLSCREEN)
    } else {
        ScreenWithToolbar(
            modifier = modifier.fillMaxSize().background(AppTheme.colors.background),
            title = stringResource(Res.string.subscriptions),
            navigationIcon = UiRes.drawable.ic_back,
            includeBottomInsets = true,
            isScrollableContent = true,
            onNavigationIconClick = onClickBack,
        ) {
            SubscriptionsScreen(
                modifier = Modifier.fillMaxSize(),
                uiState = uiState,
                onUiEvent = viewModel::onUiEvent,
                onClickUpgradePremium = { onNavigatePaywall() },
            )
        }
    }
}

@Composable
private fun SubscriptionsScreen(
    modifier: Modifier = Modifier,
    uiState: SubscriptionsUiState,
    onUiEvent: (SubscriptionsUiEvent) -> Unit,
    onClickUpgradePremium: () -> Unit = {},
) {
    val topPadding =
        if (uiState.showUpgradePremiumBanner) {
            AppTheme.spacing.defaultSpacing
        } else {
            0.dp
        }
    Column(
        modifier = modifier.padding(top = topPadding),
        verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.sectionSpacing),
    ) {
        if (uiState.showUpgradePremiumBanner) {
            UpgradePremiumBanner(
                style = UpgradePremiumBannerStyle.LARGE,
                onClick = { onClickUpgradePremium() },
            )
        }

        CurrentSubscriptionPlanAndFeatures(
            name = uiState.currentPlan?.name ?: stringResource(Res.string.subscriptions_plan_free),
            features = PremiumFeatureFactory.ofSubscription(uiState.currentPlan),
            price = uiState.currentPlan?.formattedPrice,
            duration = when (uiState.currentPlan?.durationType) {
                Subscription.DurationType.DAILY -> pluralStringResource(Res.plurals.paywall_unit_day, 1)
                Subscription.DurationType.WEEKLY -> pluralStringResource(Res.plurals.paywall_unit_week, 1)
                Subscription.DurationType.MONTHLY -> pluralStringResource(Res.plurals.paywall_unit_month, 1)
                Subscription.DurationType.TWO_MONTHS -> pluralStringResource(Res.plurals.paywall_unit_month_count, 2, 2)
                Subscription.DurationType.THREE_MONTHS -> pluralStringResource(Res.plurals.paywall_unit_month_count, 3, 3)
                Subscription.DurationType.SIX_MONTHS -> pluralStringResource(Res.plurals.paywall_unit_month_count, 6, 6)
                Subscription.DurationType.YEARLY -> pluralStringResource(Res.plurals.paywall_unit_year, 1)
                Subscription.DurationType.LIFETIME -> stringResource(Res.string.paywall_unit_lifetime)
                else -> null
            },
        )

        if (uiState.currentPlan != null) {
            ManageSubscriptionText(
                isLifetime = uiState.currentPlan.isLifetime,
                isRecurring = uiState.currentPlan.willRenew,
                expirationDate = uiState.currentPlan.expirationDateInMillis?.asFormattedDate(),
                subscriptionUrl = subscriptionUrl,
                // Mock has no store — tapping "here" cancels in-app instead of opening the URL.
                onManageClick = if (uiState.isMock) {
                    { onUiEvent(SubscriptionsUiEvent.OnCancelMockSubscription) }
                } else {
                    null
                },
            )
        }
    }
}

@Preview
@Composable
private fun SubscriptionsScreenPreview() {
    AppTheme {
        SubscriptionsScreen(uiState = SubscriptionsUiState(), onUiEvent = {})
    }
}
