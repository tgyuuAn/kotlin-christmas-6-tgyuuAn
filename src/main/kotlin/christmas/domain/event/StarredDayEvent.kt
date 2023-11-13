package christmas.domain.event

import christmas.domain.EventType
import christmas.domain.Reservation
import christmas.uitl.Calendar.STARRED_DAY

class StarredDayEvent(
    eventType: EventType,
    private val reservation: Reservation,
) : WoowaEvent(eventType, reservation) {

    override fun isEligibleDayForEvent(): Boolean = reservation.visitDate in STARRED_DAY

    override fun calculateDiscountAmount(): Int = STARRED_DAY_DISCOUNT

    companion object {
        const val STARRED_DAY_DISCOUNT = 1000
    }
}