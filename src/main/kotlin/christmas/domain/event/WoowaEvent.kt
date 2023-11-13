package christmas.domain.event

import christmas.domain.MenuItem
import christmas.domain.MenuType
import christmas.domain.Reservation

abstract class WoowaEvent(private val reservation: Reservation) {

    fun isEligibleForEvent(): Boolean {
        if (getTotalAmount(reservation.menuItems) < 10000) {
            return false
        }

        if (isAllMenusAreBeverage(reservation.menuItems)) {
            return false
        }

        if (!isEligibleDayForEvent()) {
            return false
        }

        return true
    }

    abstract fun isEligibleDayForEvent(): Boolean

    abstract fun calculateDiscountAmount(): Int

    fun getTotalAmount(orderedMenus: List<MenuItem>): Int {
        var totalAmount = 0
        orderedMenus.forEach { menuItem ->
            totalAmount += menuItem.price
        }
        return totalAmount
    }

    private fun isAllMenusAreBeverage(orderedMenus: List<MenuItem>): Boolean {
        return orderedMenus.all { it.menuType == MenuType.BEVERAGE }
    }
}