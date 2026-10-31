package tech.kaustubhdeshpande.kotlinconf27.subscription.api

data class PurchaseError(
    val message: String,
    val code: String? = null,
)
