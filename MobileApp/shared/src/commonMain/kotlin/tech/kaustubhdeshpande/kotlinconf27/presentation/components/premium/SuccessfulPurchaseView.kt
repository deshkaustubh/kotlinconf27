package tech.kaustubhdeshpande.kotlinconf27.presentation.components.premium

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import tech.kaustubhdeshpande.kotlinconf27.designsystem.components.premium.PremiumFeatureUiState
import tech.kaustubhdeshpande.kotlinconf27.designsystem.components.premium.SuccessfulPurchaseContent
import tech.kaustubhdeshpande.kotlinconf27.util.Constants.subscriptionUrl
import tech.kaustubhdeshpande.kotlinconf27.util.inappreview.rememberInAppReviewTrigger
import tech.kaustubhdeshpande.kotlinconf27.util.logging.AppLogger
import tech.kaustubhdeshpande.kotlinconf27.util.logging.logSuccessfulPurchase

@Composable
fun SuccessfulPurchaseView(
    features: List<PremiumFeatureUiState> = emptyList(),
    expirationDate: String? = null,
    isLifetime: Boolean = false,
    isRecurring: Boolean = true,
    modifier: Modifier = Modifier,
    onContinue: () -> Unit = {},
) {
    val inAppReviewTrigger = rememberInAppReviewTrigger()
    LaunchedEffect(Unit) {
        AppLogger.logSuccessfulPurchase()
        inAppReviewTrigger.triggerAfterSuccessfulPurchase()
    }

    SuccessfulPurchaseContent(
        subscriptionUrl = subscriptionUrl,
        features = features,
        expirationDate = expirationDate,
        isLifetime = isLifetime,
        isRecurring = isRecurring,
        modifier = modifier,
        onContinue = onContinue,
    )
}
