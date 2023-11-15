package christmas.domain.event

import christmas.domain.Reservation
import christmas.domain.event.FreebieEvent.Companion.MINIMUM_AMOUNT_FOR_FREEBIE

abstract class WoowaEvent(
    private val event: Event,
    private val reservation: Reservation
) {
    abstract fun isEligibleDayForEvent(): Boolean

    abstract fun calculateDiscountAmount(): Int

    fun isEligibleForEvent(): Boolean {
        if (!isTotalAmountEligible()) {
            return false
        }
        if (reservation.isAllMenusAreBeverage()) {
            return false
        }
        if (!isEligibleDayForEvent()) {
            return false
        }
        return true
    }

    private fun isTotalAmountEligible(): Boolean {
        if (event == Event.FREEBIE) {
            return reservation.getTotalAmount() >= MINIMUM_AMOUNT_FOR_FREEBIE
        }
        return reservation.getTotalAmount() >= EVENT_MINIMUM_AMOUNT
    }

    companion object {
        private const val EVENT_MINIMUM_AMOUNT = 10000
    }
}