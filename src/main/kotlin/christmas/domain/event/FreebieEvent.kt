package christmas.domain.event

import christmas.domain.EventType
import christmas.domain.MenuItem.*
import christmas.domain.Reservation

class FreebieEvent(
    eventType: EventType,
    reservation: Reservation,
) : WoowaEvent(eventType, reservation) {

    override fun isEligibleDayForEvent(): Boolean = true

    override fun calculateDiscountAmount(): Int = CHAMPAGNE.price

    companion object{
        const val MINIMUM_AMOUNT_FOR_FREEBIE = 120000
    }
}