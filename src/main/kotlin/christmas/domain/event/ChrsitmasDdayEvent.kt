package christmas.domain.event

import christmas.domain.Reservation
import christmas.uitl.MoneyUnit.HUNDRED_UNIT

class ChristmasDdayEvent(
    private val reservation: Reservation
) : WoowaEvent(reservation) {

    override fun isEligibleDayForEvent(): Boolean {
        return reservation.visitDate in EVENT_START_DAY..EVENT_END_DAY
    }

    override fun calculateDiscountAmount(): Int {
        return 1000 + (reservation.visitDate - EVENT_START_DAY) * HUNDRED_UNIT
    }

    companion object {
        private const val EVENT_START_DAY = 1
        private const val EVENT_END_DAY = 25
    }
}