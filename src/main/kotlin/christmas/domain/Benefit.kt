package christmas.domain

import christmas.domain.event.EventType
import christmas.util.StringFormatter.decimalFormat

class Benefit {
    var totalDiscountedAmount: Int = 0
        private set

    val discountedAmount: HashMap<EventType, Int> = hashMapOf()

    fun accumulateBenefit(eventType: EventType, discountedAmount: Int) {
        updateTotalDiscountedAmount(eventType, discountedAmount)
    }

    private fun updateTotalDiscountedAmount(eventType: EventType, amount: Int) {
        discountedAmount.put(eventType, amount)
        totalDiscountedAmount += amount
    }

    override fun toString(): String {
        val result = StringBuilder()
        discountedAmount.forEach { eventType, amount ->
            result.append("${eventType.eventDescription} : -" + decimalFormat.format(amount) + "원\n")
        }
        return result.toString()
    }
}