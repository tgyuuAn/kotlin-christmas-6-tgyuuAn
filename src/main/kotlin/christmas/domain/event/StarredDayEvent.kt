package christmas.domain.event

import christmas.domain.Reservation
import christmas.util.Calendar.STARRED_DAY

class StarredDayEvent(
    event: Event,
    private val reservation: Reservation,
) : WoowaEvent(event, reservation) {

    override fun isEligibleDayForEvent(): Boolean = reservation.visitDate in STARRED_DAY

    override fun calculateDiscountAmount(): Int = STARRED_DAY_DISCOUNT_AMOUNT

    companion object {
        const val STARRED_DAY_DISCOUNT_AMOUNT = 1000
    }
}