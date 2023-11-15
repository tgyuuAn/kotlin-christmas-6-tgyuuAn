package christmas.util

import java.text.DecimalFormat

object StringFormatter {
    val decimalFormat = DecimalFormat("#,###")

    fun formatAmountWithWon(amount: String): String {
        val formattedAmount = decimalFormat.format(amount)
        return "$formattedAmount 원"
    }
}