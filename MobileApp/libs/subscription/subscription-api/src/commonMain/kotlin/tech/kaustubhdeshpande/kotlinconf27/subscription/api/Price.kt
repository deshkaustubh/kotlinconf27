package tech.kaustubhdeshpande.kotlinconf27.subscription.api

data class Price(
    val amount: Float,
    val currencyCodeOrSymbol: String? = null,
    val localizedString: String? = null,
)
