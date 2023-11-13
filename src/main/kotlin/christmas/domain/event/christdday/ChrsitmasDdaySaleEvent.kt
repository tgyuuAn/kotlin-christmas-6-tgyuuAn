package christmas.domain.event.christdday

import christmas.domain.MenuCategory
import christmas.domain.event.woowaEvent

class ChristmasDdayEvent() : woowaEvent() {
    override fun calculateDiscountAmount(): Long {
        return 0.toLong()
    }

    override fun isEligibleForEvent(
        reservationDate: Int,
        orderedMenu: List<MenuCategory>?
    ): Boolean {
        TODO("Not yet implemented")
    }

}