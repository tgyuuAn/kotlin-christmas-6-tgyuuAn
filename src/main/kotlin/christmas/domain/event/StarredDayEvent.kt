package christmas.domain.event

import christmas.domain.Reservation
import christmas.uitl.Calendar.STARRED_DAY

class StarredDayEvent(private val reservation: Reservation) : WoowaEvent(reservation) {
    override fun isEligibleDayForEvent(): Boolean = reservation.visitDate in STARRED_DAY

    override fun calculateDiscountAmount(): Int = STARRED_DAY_DISCOUNT

    companion object {
        const val STARRED_DAY_DISCOUNT = 1000
    }
}