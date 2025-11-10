package com.chargeback.app.utils

import com.chargeback.app.R

data class IconOption(
    val iconRes: Int,
    val label: String,
    val price: Double? = null,
    val currencyCode: String? = null
)

object Constants {

    object ServiceObject {
        const val SER_NETFLIX = "Netflix"
        const val SER_HULU = "Hulu"
        const val SER_SPOTIFY = "Spotify"
        const val SER_PLAYSTATION = "PlayStation+"
        const val SER_PARAMOUNT = "Paramount+"
        const val SER_YOUTUBE_MUSIC = "YouTube Music"
        const val SER_DISNEY = "Disney+"

        val options = listOf(
            IconOption(R.drawable.ic_netflix, SER_NETFLIX, price = 15.99, currencyCode = "USD"),
            IconOption(R.drawable.ic_hulu, SER_HULU, price = 7.99, currencyCode = "USD"),
            IconOption(R.drawable.ic_spotify, SER_SPOTIFY, price = 10.99, currencyCode = "USD"),
            IconOption(R.drawable.ic_playstation, SER_PLAYSTATION, price = 12.99, currencyCode = "USD"),
            IconOption(R.drawable.ic_paramount, SER_PARAMOUNT, price = 9.99, currencyCode = "USD"),
            IconOption(R.drawable.ic_youtube_music, SER_YOUTUBE_MUSIC, price = 10.99, currencyCode = "USD"),
            IconOption(R.drawable.ic_disney, SER_DISNEY, price = 13.99, currencyCode = "USD")
        )

        fun iconFor(label: String?): Int? = options.firstOrNull { it.label == label }?.iconRes

        fun optionFor(label: String?): IconOption? = options.firstOrNull { it.label == label }
    }

    object CategoryObject {
        const val CAT_SUBSCRIPTION = "Subscription"
        const val CAT_UTILITY = "Utility"
        const val CAT_CARD = "Card Payment"
        const val CAT_LOAN = "Loan"
        const val CAT_RENT = "Rent"

        val options = listOf(
            IconOption(R.drawable.ic_refresh, CAT_SUBSCRIPTION),
            IconOption(R.drawable.ic_tool, CAT_UTILITY),
            IconOption(R.drawable.ic_credit_card, CAT_CARD),
            IconOption(R.drawable.ic_coin, CAT_LOAN),
            IconOption(R.drawable.ic_home, CAT_RENT)
        )

        fun iconFor(label: String?): Int? = options.firstOrNull { it.label == label }?.iconRes
    }

    object FrequencyObject {
        const val WEEKLY = "Weekly"
        const val MONTHLY = "Monthly"
        const val ANNUALLY = "Annually"

        val options = listOf(WEEKLY, MONTHLY, ANNUALLY)
    }
}