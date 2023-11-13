package christmas.domain.event

import christmas.domain.Reservation

class WeekendEvent(private val reservation: Reservation) : WoowaEvent(reservation) {
    override fun isEligibleDayForEvent(): Boolean {
        TODO("Not yet implemented")
    }

    override fun calculateDiscountAmount(): Int {
        TODO("Not yet implemented")
    }

    companion object {
        const val DISCOUNT_PER_MAIN = 2023
    }
}