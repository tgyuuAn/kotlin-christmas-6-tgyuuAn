package christmas.domain

import christmas.domain.event.EventType
import christmas.util.StringFormatter.decimalFormat

class Benefit {
    val discountedAmount: HashMap<EventType, Int> = hashMapOf()

    fun accumulateBenefit(eventType: EventType, discountedAmount: Int) {
        updateTotalDiscountedAmount(eventType, discountedAmount)
    }

    private fun updateTotalDiscountedAmount(eventType: EventType, amount: Int) {
        discountedAmount.put(eventType, amount)
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