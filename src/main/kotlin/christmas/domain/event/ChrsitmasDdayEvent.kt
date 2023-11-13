package christmas.domain.event


import christmas.domain.Reservation
import java.lang.Integer.min

class ChristmasDdayEvent(
    private val reservation: Reservation
) : WoowaEvent(reservation) {

    override fun isEligibleDayForEvent(): Boolean {
        return reservation.visitDate in EVENT_START_DAY..EVENT_END_DAY
    }

    override fun calculateDiscountAmount(): Int {
        val totalAmount = getTotalAmount(reservation.menuItems)

        return min(totalAmount, 1000 + (reservation.visitDate - EVENT_START_DAY) * 100)
    }

    companion object {
        private const val EVENT_START_DAY = 1
        private const val EVENT_END_DAY = 25
    }
}