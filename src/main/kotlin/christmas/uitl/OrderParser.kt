package christmas.uitl

import christmas.domain.menu.MenuItem
import christmas.domain.validator.InputValidator.validateInputIsInt

object OrderParser {
    fun parseOrdersAndValidate(orders: List<String>): List<Pair<MenuItem, Int>> = orders.map {
        val splited = it.trim().split("-").map { it.trim() }
        validateInputIsInt(splited[1], "유효하지 않은 주문입니다.")
        validateInputIsMenuItem(splited[0], "유효하지 않은 주문입니다.")
        splited[1] to splited[1].toInt()
    }
}