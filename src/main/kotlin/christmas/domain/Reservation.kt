package christmas.domain

import christmas.domain.menu.MenuCategory.BEVERAGE
import christmas.domain.menu.OrderMenu

data class Reservation(
    val orderedMenus: List<OrderMenu>,
    val visitDate: Int,
) {
    init {
        validateOrderMenusExceeded()
    }

    private fun validateOrderMenusExceeded() {
        check(getTotalMenusCount() <= 20) {
            "[ERROR] 주문할 수 있는 메뉴의 개수는 최대 20개 입니다."
        }
    }

    fun getTotalAmount(): Int = orderedMenus.sumOf { it.menuItem.price * it.orderedCount }

    fun isAllMenusAreBeverage(): Boolean =
        orderedMenus.all { it.menuItem.menuCategory == BEVERAGE }

    private fun getTotalMenusCount(): Int = orderedMenus.sumOf { it.orderedCount }
}