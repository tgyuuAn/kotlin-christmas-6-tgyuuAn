package christmas.domain

import christmas.domain.event.EventType
import christmas.util.StringFormatter.decimalFormat

class Benefit {
    private var eventType: EventType? = null
    private var discountedAmount: Int? = null
    var totalDiscountedAmount: Int = 0
        private set

    fun accumulateBenefit(eventType: EventType, discountedAmount: Int) {
        this.eventType = eventType
        this.discountedAmount = discountedAmount
        totalDiscountedAmount += discountedAmount
    }

    override fun toString(): String =
        "${eventType?.eventDescription} : -" + decimalFormat.format(discountedAmount) + "원"
}