package christmas.domain.event

import christmas.domain.menu.MenuItem.*
import christmas.domain.Reservation

class FreebieEvent(
    eventType: EventType,
    private val reservation: Reservation,
) : WoowaEvent(eventType, reservation) {

    override fun isEligibleDayForEvent(): Boolean =
        reservation.visitDate in EVENT_START_DAY..EVENT_END_DAY

    override fun calculateDiscountAmount(): Int = CHAMPAGNE.price

    companion object {
        private const val EVENT_START_DAY = 1
        private const val EVENT_END_DAY = 31
        const val MINIMUM_AMOUNT_FOR_FREEBIE = 120000
    }
}