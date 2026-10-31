package tech.kaustubhdeshpande.kotlinconf27.presentation.screens.creditbalance

import tech.kaustubhdeshpande.kotlinconf27.domain.model.credit.CreditTransaction
import tech.kaustubhdeshpande.kotlinconf27.domain.model.credit.RecurringCredit

data class CreditBalanceUiState(
    val creditBalance: Int = 0,
    val isPremiumUser: Boolean = false,
    val isPremiumRequired: Boolean = false,
    val isMoreCreditRequired: Boolean = false,
    val lastTransactions: List<CreditTransaction> = emptyList(),
    val recurringCredits: List<RecurringCredit> = emptyList(),
)

sealed interface CreditBalanceUiEvent {
    data object UpgradeToPremium : CreditBalanceUiEvent
    data object BuyCreditPack : CreditBalanceUiEvent
}
