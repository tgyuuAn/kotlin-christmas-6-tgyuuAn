package christmas.domain

import christmas.domain.menu.MenuCategory
import christmas.domain.menu.OrderMenu

data class Reservation(
    val orderedMenus: List<OrderMenu>,
    val visitDate: Int,
) {

    init {
        validateOrderMenusExceeded()
    }

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

    private fun validateOrderMenusExceeded() {
        check(getTotalMenusCount() <= 20) {
            "[ERROR] 주문할 수 있는 메뉴의 개수는 최대 20개 입니다."
        }
    }

    private fun getTotalMenusCount(): Int {
        var totalOrderMenusCount = 0
        orderedMenus.map { orderMenu ->
            totalOrderMenusCount += orderMenu.orderedCount
        }
        return totalOrderMenusCount
    }
}