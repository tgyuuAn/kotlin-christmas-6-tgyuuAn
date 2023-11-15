package christmas.domain

import christmas.domain.event.Event
import christmas.domain.event.EventFactory
import christmas.util.StringFormatter.decimalFormat

class Benefit(private val reservation: Reservation) {

    init{
        calculateTotalBenefits()
    }

    private val _discountedAmount: HashMap<Event, Int> = hashMapOf()

    val discountedAmount: Map<Event, Int>
        get() = _discountedAmount.toMap()

    private fun calculateTotalBenefits() {
        Event.values().forEach { eventType ->
            val event = EventFactory.getEvent(eventType, reservation)
            if (event.isEligibleForEvent()) {
                val discountAmount = event.calculateDiscountAmount()
                accumulateBenefit(eventType, discountAmount)
            }
        }
    }

    private fun accumulateBenefit(event: Event, discountedAmount: Int) {
        _discountedAmount.put(event, discountedAmount)
    }

    fun getTotalDiscountedAmount(): Int {
        var totalDiscountedAmount = 0
        _discountedAmount.forEach { eventType, amount ->
            totalDiscountedAmount += amount
        }
        return totalDiscountedAmount
    }

    override fun toString(): String {
        val result = StringBuilder()
        _discountedAmount.forEach { eventType, amount ->
            if (amount != 0) {
                result.append("${eventType.eventDescription} : -" + decimalFormat.format(amount) + "원\n")
            }
        }
        return result.toString()
    }
}