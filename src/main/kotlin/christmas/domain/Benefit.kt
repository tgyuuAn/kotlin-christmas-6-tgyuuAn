package christmas.domain

import christmas.domain.event.Event
import christmas.util.StringFormatter.decimalFormat

class Benefit {
    val discountedAmount: HashMap<Event, Int> = hashMapOf()

    fun accumulateBenefit(event: Event, discountedAmount: Int) {
        updateTotalDiscountedAmount(event, discountedAmount)
    }

    private fun updateTotalDiscountedAmount(event: Event, amount: Int) {
        discountedAmount.put(event, amount)
    }

    fun getTotalDiscountedAmount(): Int {
        var totalDiscountedAmount = 0
        discountedAmount.forEach { eventType, amount ->
            totalDiscountedAmount += amount
        }
        return totalDiscountedAmount
    }

    override fun toString(): String {
        val result = StringBuilder()
        discountedAmount.forEach { eventType, amount ->
            if (amount != 0) {
                result.append("${eventType.eventDescription} : -" + decimalFormat.format(amount) + "원\n")
            }
        }
        return result.toString()
    }
}