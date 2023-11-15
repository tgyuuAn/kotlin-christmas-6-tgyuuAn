package christmas.domain.event

import christmas.domain.menu.MenuItem.*
import christmas.domain.Reservation
import christmas.util.Calendar.DECEMBER_END_DAY
import christmas.util.Calendar.DECEMBER_START_DAY

class FreebieEvent(
    event: Event,
    private val reservation: Reservation,
) : WoowaEvent(event, reservation) {

    override fun isEligibleDayForEvent(): Boolean =
        reservation.visitDate in DECEMBER_START_DAY..DECEMBER_END_DAY

    override fun calculateDiscountAmount(): Int = EVENT_FREEBIE.price * FREEBIE_COUNT

    companion object {
        val EVENT_FREEBIE = CHAMPAGNE
        val FREEBIE_COUNT = 1
        const val MINIMUM_AMOUNT_FOR_FREEBIE = 120000
    }
}