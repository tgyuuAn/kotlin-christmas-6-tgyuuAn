package christmas.domain.event

import christmas.domain.EventType
import christmas.domain.Reservation
import christmas.uitl.MoneyUnit.HUNDRED_UNIT

class ChristmasDdayEvent(
    private val eventType: EventType,
    private val reservation: Reservation,
) : WoowaEvent(eventType, reservation) {

    override fun isEligibleDayForEvent(): Boolean =
        reservation.visitDate in EVENT_START_DAY..EVENT_END_DAY

    override fun calculateDiscountAmount(): Int =
        CHRISTMAS_DDAY_EVENT_DEFAULT_SALE_AMOUNT + (reservation.visitDate - EVENT_START_DAY) * HUNDRED_UNIT

    companion object {
        private const val EVENT_START_DAY = 1
        private const val EVENT_END_DAY = 25
        private const val CHRISTMAS_DDAY_EVENT_DEFAULT_SALE_AMOUNT = 1000
    }
}