package com.chargeback.app.utils

import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

fun formatCurrency(
    price: Double?,
    currencyCode: String?,
    locale: Locale = Locale.US
): String? {
    val value = price ?: return null
    val formatter = NumberFormat.getCurrencyInstance(locale)
    currencyCode?.let {
        runCatching { Currency.getInstance(it) }.getOrNull()?.let { currency ->
            formatter.currency = currency
        }
    }
    return formatter.format(value)
}

