package christmas.util

import christmas.domain.menu.MenuItem
import christmas.validator.InputValidator.validateInputIsInt
import christmas.validator.InputValidator.validateInputIsMenuItem

object OrderParser {
    fun parseOrders(input: String): List<Pair<MenuItem, Int>> =
        input.split(",").map { parseOrder(it) }

    fun parseOrder(order: String): Pair<MenuItem, Int> {
        val ordersSplited = order.trim().split("-").map { it.trim() }
        val menuItem = getMenuItemOrThrow(ordersSplited[0])
        val menuCount = getMenuCountOrThrow(ordersSplited[1])
        return menuItem to menuCount
    }

    private fun getMenuItemOrThrow(input: String): MenuItem =
        validateInputIsMenuItem(input, "유효하지 않은 주문입니다.")
            .onFailure { throw it }
            .getOrThrow()

    private fun getMenuCountOrThrow(input: String): Int =
        validateInputIsInt(input, "유효하지 않은 주문입니다.")
            .onFailure { throw it }
            .getOrThrow()

}