package christmas.domain.event

import christmas.domain.Reservation
import christmas.uitl.Calendar.FRIDAY
import christmas.uitl.Calendar.SATURDAY
import christmas.uitl.Calendar.WEEK_LENGTH

class WeekdayEvent(private val reservation: Reservation) : WoowaEvent(reservation) {

    override fun isEligibleDayForEvent(): Boolean {
        val day = (reservation.visitDate % WEEK_LENGTH)
        return day != FRIDAY && day != SATURDAY
    }

    override fun calculateDiscountAmount(): Int {
        return reservation.getTotalAmount()
    }
}