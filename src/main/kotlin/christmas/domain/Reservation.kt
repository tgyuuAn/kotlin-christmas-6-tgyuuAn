package christmas.domain

import christmas.domain.menu.MenuCategory
import christmas.domain.menu.OrderMenu

data class Reservation(
    val orderedMenus: List<OrderMenu>,
    val visitDate: Int,
) {

    fun getTotalAmount(): Int {
        var totalAmount = 0
        orderedMenus.forEach { orderedMenu ->
            val menuPrice = orderedMenu.menuItem.price
            val menuCount = orderedMenu.orderedCount
            totalAmount += menuPrice * menuCount
        }
        return totalAmount
    }

    fun isAllMenusAreBeverage(): Boolean =
        orderedMenus.all { it.menuItem.menuCategory == MenuCategory.BEVERAGE }
}