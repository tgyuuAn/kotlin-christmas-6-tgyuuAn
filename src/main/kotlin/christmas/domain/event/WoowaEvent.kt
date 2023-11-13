package christmas.domain.event

import christmas.domain.MenuType.*
import christmas.domain.Reservation

abstract class WoowaEvent(private val reservation: Reservation) {

    abstract fun isEligibleDayForEvent(): Boolean

    abstract fun calculateDiscountAmount(): Int

    fun isEligibleForEvent(): Boolean {
        if (!isTotalAmountEligible()) {
            return false
        }
        if (isAllMenusAreBeverage()) {
            return false
        }
        if (!isEligibleDayForEvent()) {
            return false
        }
        return true
    }

    private fun isTotalAmountEligible(): Boolean =
        reservation.getTotalAmount() >= EVENT_MINIMUM_AMOUNT

    private fun isAllMenusAreBeverage(): Boolean =
        reservation.orderedMenus.all { it.menuType == BEVERAGE }

    companion object {
        private const val EVENT_MINIMUM_AMOUNT = 10000
    }
}