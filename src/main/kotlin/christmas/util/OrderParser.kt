package christmas.util

import christmas.domain.menu.OrderMenu
import christmas.domain.menu.MenuItem
import christmas.validator.InputValidator.validateInputIsInt
import christmas.validator.InputValidator.validateInputIsMenuItem

object OrderParser {

    private const val INVALID_ORDER_MESSAGE = "유효하지 않은 주문입니다."

    fun parseOrders(input: String): List<OrderMenu> =
        input.split(",").map { parseOrder(it) }

    private fun parseOrder(order: String): OrderMenu {
        val ordersSplited = order.trim().split("-").map { it.trim() }
        val menuItem = getMenuItemOrThrow(ordersSplited[0])
        val menuCount = getMenuCountOrThrow(ordersSplited[1])
        return OrderMenu(menuItem, menuCount)
    }

    private fun getMenuItemOrThrow(input: String): MenuItem =
        validateInputIsMenuItem(input, INVALID_ORDER_MESSAGE)
            .onFailure { throw it }
            .getOrThrow()

    private fun getMenuCountOrThrow(input: String): Int =
        validateInputIsInt(input, INVALID_ORDER_MESSAGE)
            .onFailure { throw it }
            .getOrThrow()

}