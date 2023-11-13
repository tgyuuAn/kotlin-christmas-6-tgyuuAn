package christmas.domain.event.christdday

import christmas.domain.event.woowaEvent

class ChristmasDdayEvent() : woowaEvent() {
    override fun calculateDiscountAmount(): Long {
        return 0.toLong()
    }

    override fun isEligibleForEvent(reservationDate: Int): Boolean {
        TODO("Not yet implemented")
    }
}