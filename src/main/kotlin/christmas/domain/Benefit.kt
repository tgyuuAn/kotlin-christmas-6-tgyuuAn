package christmas.domain

import christmas.domain.event.Event
import christmas.domain.event.EventFactory
import christmas.util.StringFormatter.decimalFormat

class Benefit(private val reservation: Reservation) {

    private val _discountedAmount: HashMap<Event, Int> = hashMapOf()

    val discountedAmount: Map<Event, Int>
        get() = _discountedAmount.toMap()

    init {
        calculateTotalBenefits()
    }

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
        _discountedAmount.forEach { event, amount ->
            totalDiscountedAmount += amount
        }
        return totalDiscountedAmount
    }

    override fun toString(): String {
        if (_discountedAmount.isEmpty()) {
            return "없음"
        }

        val result = StringBuilder()
        _discountedAmount.forEach { eventType, amount ->
            if (amount != 0) {
                result.append("${eventType.eventDescription}: -" + decimalFormat.format(amount)+"\n")
            }
        }
        return result.toString().trim()
    }
}