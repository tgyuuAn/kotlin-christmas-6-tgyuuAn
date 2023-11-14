package christmas.domain

import christmas.domain.menu.MenuItem
import christmas.domain.menu.MenuType

data class Reservation(
    val orderedMenus: List<Pair<MenuItem, Int>>,
    val visitDate: Int,
) {

    fun getTotalAmount(): Int {
        var totalAmount = 0
        orderedMenus.forEach { orderedMenu ->
            val menuPrice = orderedMenu.first.price
            val menuCount = orderedMenu.second
            totalAmount += menuPrice * menuCount
        }
        return totalAmount
    }

    fun isAllMenusAreBeverage(): Boolean =
        orderedMenus.all { it.first.menuType == MenuType.BEVERAGE }
}