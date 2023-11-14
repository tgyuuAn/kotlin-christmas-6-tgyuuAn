package christmas.domain

import christmas.domain.menu.MenuItem
import christmas.domain.menu.MenuType

data class Reservation(
    val orderedMenus: List<MenuItem>,
    val visitDate: Int,
) {

    fun getTotalAmount(): Int {
        var totalAmount = 0
        orderedMenus.forEach { orderedMenu ->
            totalAmount += orderedMenu.price
        }
        return totalAmount
    }

    fun isAllMenusAreBeverage(): Boolean =
        orderedMenus.all { it.menuType == MenuType.BEVERAGE }
}