package christmas.domain.event

import java.awt.Menu

abstract class woowaEvent {
    abstract fun calculateDiscountAmount(): Long
    abstract fun isEligibleDayForEvent(reservationDate: Int, orderedMenu: List<Menu>): Boolean)
}